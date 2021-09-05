package io.github.cataline.startup;

import io.github.juli.logging.Log;
import io.github.juli.logging.LogFactory;

import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ClassLoaderFactory {
    private static final Log log = LogFactory.getLog(ClassLoaderFactory.class);

    /**
     * 创建classLoader
     * @param repositories 资源路径和类型
     * @param parent   加载器
     * @return
     */
    public static ClassLoader createClassLoader(List<Repository> repositories, final ClassLoader parent) {
        if (log.isDebugEnabled()) {
            log.dubug("创建一个新的class loader");
        }
        //存放 构建的 class path
        Set<URL> set = new LinkedHashSet<>();
        final URL[] array = set.toArray(new URL[0]);
        return AccessController.doPrivileged(
                (PrivilegedAction<URLClassLoader>) () -> {
                    if (parent == null) {
                        return new URLClassLoader(array);
                    } else {
                        return new URLClassLoader(array, parent);
                    }
                });
    }

    public enum RepositoryType {
        DIR,
        GLOB,
        JAR,
        URL
    }

    public static class Repository {
        private String location;
        private RepositoryType repositoryType;

        public Repository(String location, RepositoryType type) {
            this.location = location;
            this.repositoryType = type;
        }

        public String getLocation() {
            return location;
        }


        public RepositoryType getRepositoryType() {
            return repositoryType;
        }

    }
}
