package io.github.cataline.startup;

import io.github.juli.logging.Log;
import io.github.juli.logging.LogFactory;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

public class Bootstrap {
    private static final Log log = LogFactory.getLog(Bootstrap.class);
    private static final Object daemonLock = new Object();
    private static volatile Bootstrap daemon = null;

    private static final File catalinaBaseFile;
    private static final File catalineHomeFile;

    private static final Pattern PATH_PATTERN = Pattern.compile("(\"[^\"]*\")|(([^,])*)");

    static {
        String userDir = System.getProperty("user.dir");
        System.out.println("userdir=" +userDir);
        //获取系统变量中的catalina.home目录
        String home = System.getProperty(Constants.CATALINA_HOME_PROP);
        File homeFile = null;
        if (home != null) {
            File f = new File(home);
            try {
                homeFile = f.getCanonicalFile();
            } catch (IOException ioe) {
                homeFile = f.getAbsoluteFile();
            }
        }
        //在catalina.home路径无文件
        if (homeFile == null) {
            //当前目录是否是Tomcat的bin目录
            File bootstrapJar = new File(userDir, "bootstrap.jar");
            //是bin目录
            if (bootstrapJar.exists()) {
                //返回上一级目录
                File f = new File(userDir, "..");
                try {
                    homeFile = f.getCanonicalFile();
                } catch (IOException e) {
                    homeFile = f.getAbsoluteFile();
                }
            }
        }
        //不是Tomcat的目录,使用当前目录
        if (homeFile == null) {
            File f = new File(userDir);
            try {
                homeFile = f.getCanonicalFile();
            } catch (IOException ioe) {
                homeFile = f.getAbsoluteFile();
            }
        }

        //赋值给catalinaHomeFile 并设置在系统变量中
        catalineHomeFile = homeFile;
        System.setProperty(Constants.CATALINA_HOME_PROP, catalineHomeFile.getPath());


        String base = System.getProperty(Constants.CATALINA_BASE_PROP);
        if (base == null) {
            catalinaBaseFile = catalineHomeFile;
        } else {
            File baseFile = new File(base);
            try {
                baseFile = baseFile.getCanonicalFile();
            } catch (IOException ioe) {
                baseFile = baseFile.getAbsoluteFile();
            }
            catalinaBaseFile = baseFile;
        }
        System.setProperty(Constants.CATALINA_BASE_PROP, catalinaBaseFile.getPath());
    }

    // ---------- 参数
    private Object catalinaDaemon = null;
    ClassLoader commonLoader = null;
    ClassLoader catalinaLoader = null;
    ClassLoader sharedLoader = null;

    static void handlerThrowable(Throwable t) {
        if (t instanceof ThreadDeath) {
            throw (ThreadDeath) t;
        }
        if (t instanceof StackOverflowError) {
            return;
        }
        if (t instanceof VirtualMachineError) {
            throw (VirtualMachineError) t;
        }
    }

    public static void main(String[] args) {

    }
}
