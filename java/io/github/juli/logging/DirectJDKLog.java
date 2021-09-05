package io.github.juli.logging;

import java.util.logging.*;

/**
 * 日志接口的实现  搞得是jdk的那一套
 * 静态代理
 */
public class DirectJDKLog implements Log {
    private final Logger logger;
    //配置读和输出格式
    public static final String SIMPLE_FMT = "java.util.logging.SimpleFormatter";
    public static final String FORMATTER = "org.apache.juli.formatter";

    static {
        System.out.println(System.getProperty("java.util.logging.config.class"));
        System.out.println(System.getProperty("java.util.logging.config.file"));
        if (
                System.getProperty("java.util.logging.config.class") == null &&
                        System.getProperty("java.util.logging.config.file") == null
        ) {
            //设置默认的配置
            try {
                //加载jdk的日志框架
                Formatter fmt = (Formatter) Class.forName(System.getProperty(FORMATTER, SIMPLE_FMT)).getConstructor().newInstance();
                Logger root = Logger.getLogger("");
                Handler[] handlers = root.getHandlers();
                for (Handler handler : handlers) {
                    if (handler instanceof ConsoleHandler) {
                        handler.setFormatter(fmt);
                    }
                }

            } catch (Throwable e) {
                //抛异常不会有啥输出
            }
        }
    }

    public DirectJDKLog(String name) {
        logger = Logger.getLogger(name);
    }

    @Override
    public boolean isDebugEnabled() {
        return logger.isLoggable(Level.FINE);
    }

    @Override
    public boolean isErrorEnabled() {
        return logger.isLoggable(Level.SEVERE);
    }

    @Override
    public boolean isFatalEnabled() {
        return logger.isLoggable(Level.SEVERE);
    }

    @Override
    public boolean isInfoEnabled() {
        return logger.isLoggable(Level.INFO);
    }

    @Override
    public boolean isTraceEnabled() {
        return logger.isLoggable(Level.FINER);
    }

    @Override
    public boolean isWarnEnabled() {
        return logger.isLoggable(Level.WARNING);
    }

    @Override
    public void trace(Object msg) {
        log(Level.FINER, String.valueOf(msg), null);
    }

    @Override
    public void trace(Object msg, Throwable t) {
        log(Level.FINER, String.valueOf(msg), t);
    }

    @Override
    public void dubug(Object msg) {
        log(Level.FINE, String.valueOf(msg), null);
    }

    @Override
    public void dubug(Object msg, Throwable t) {
        log(Level.FINE, String.valueOf(msg), t);
    }

    @Override
    public void info(Object msg) {
        log(Level.INFO, String.valueOf(msg), null);
    }

    @Override
    public void info(Object msg, Throwable t) {
        log(Level.INFO, String.valueOf(msg), t);
    }

    @Override
    public void warn(Object msg) {
        log(Level.WARNING, String.valueOf(msg), null);
    }

    @Override
    public void warn(Object msg, Throwable t) {
        log(Level.WARNING, String.valueOf(msg), t);
    }

    @Override
    public void error(Object msg) {
        log(Level.SEVERE, String.valueOf(msg), null);
    }

    @Override
    public void error(Object msg, Throwable t) {
        log(Level.SEVERE, String.valueOf(msg), t);
    }

    @Override
    public void fatal(Object msg) {
        log(Level.SEVERE, String.valueOf(msg), null);
    }

    @Override
    public void fatal(Object msg, Throwable t) {
        log(Level.SEVERE, String.valueOf(msg), t);
    }

    private void log(Level level, String msg, Throwable ex) {
        if (logger.isLoggable(level)) {
            //获取异常栈
            Throwable dummyException = new Throwable();
            StackTraceElement[] stackTrace = dummyException.getStackTrace();
            String cname = "unknown";
            String method = "unknown";
            if (stackTrace != null && stackTrace.length > 2) {
                StackTraceElement caller = stackTrace[2];
                cname = caller.getClassName();
                method = caller.getMethodName();
            }
            if (ex == null) {
                logger.logp(level, cname, method, msg);
            } else {
                logger.logp(level, cname, method, msg, ex);
            }
        }
    }

    static Log getInstance(String name) {
        return new DirectJDKLog(name);
    }
}
