package io.github.servlet;

/**
 * 异步事件
 */
public class AsyncEvent {

    private final AsyncContext context;
    private final ServletRequest request;
    private final ServletResponse response;
    private final Throwable throwable;

    public AsyncEvent(AsyncContext context) {
        throwable = null;
        this.context = context;
        this.request = null;
        this.response = null;
    }

    public AsyncEvent(AsyncContext context, ServletRequest request, ServletResponse response) {
        this.context = context;
        this.request = request;
        this.response = response;
        this.throwable = null;
    }

    public AsyncEvent(AsyncContext context, Throwable trowable) {
        this.context = context;
        this.throwable = trowable;
        this.response = null;
        this.request = null;
    }

    public AsyncEvent(AsyncContext context, ServletRequest request, ServletResponse response, Throwable throwable) {
        this.context = context;
        this.request = request;
        this.response = response;
        this.throwable = throwable;
    }

    public AsyncContext getAsyncContext() {
        return context;
    }

    public ServletRequest getSuppliedRequest() {
        return request;
    }

    public ServletResponse getSuppliedResponse() {
        return response;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
