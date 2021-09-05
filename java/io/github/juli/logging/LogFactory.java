package io.github.juli.logging;

import aQute.bnd.annotation.spi.ServiceConsumer;

import java.lang.reflect.Constructor;
import java.nio.file.FileSystems;
import java.util.ServiceLoader;

@ServiceConsumer(value = Log.class)
public class LogFactory {
    private static final LogFactory singleton = new LogFactory();

    private final Constructor<? extends Log> discoveredLogConstructor;

    private LogFactory() {
        FileSystems.getDefault();
        Constructor<? extends Log> m = null;
        //SPI
        ServiceLoader<? extends Log> logLoader = ServiceLoader.load(Log.class);
        for (Log log : logLoader) {
            Class<? extends Log> c = log.getClass();
            try {
                m = c.getConstructor(String.class);
                break;
            } catch (NoSuchMethodException | SecurityException e) {

            }
        }
        discoveredLogConstructor = m;
    }

    /**
     * @return 返回LogFactory实例
     */
    public static LogFactory getFactory() throws LogConfigurationException {
        return singleton;
    }

    // 通过Class 获取 Log的子类实例对象
    public static Log getLog(Class<?> clazz) throws LogConfigurationException {
        return getFactory().getInstance(clazz.getName());
    }

    public static Log getLog(String name) throws LogConfigurationException {
        return getFactory().getInstance(name);
    }

    public Log getInstance(Class<?> clazz) throws LogConfigurationException {
        return getInstance(clazz.getName());
    }

    public Log getInstance(String name) throws LogConfigurationException {
        if (discoveredLogConstructor == null) {
            //默认的JDK代理Log
            return DirectJDKLog.getInstance(name);
        }
        try {
            return discoveredLogConstructor.newInstance(name);
        } catch (ReflectiveOperationException | IllegalArgumentException e) {
            throw new LogConfigurationException(e);
        }
    }
}
