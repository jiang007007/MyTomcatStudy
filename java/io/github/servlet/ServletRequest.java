package io.github.servlet;

public interface ServletRequest {

    public AsyncContext startAsync(ServletRequest servletRequest,
                                   ServletResponse servletResponse
    ) throws IllegalAccessException;

    public boolean isAsyncStarted();

    public boolean isAsyncSupported();

    public AsyncContext getAsyncContext();
}
