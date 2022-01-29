package io.github.servlet;

public interface AsyncContext {


    void complete();

    void start(Runnable run);

    void addListener(AsyncListener listener,ServletRequest request,ServletResponse response);

    <T extends AsyncListener> T createListener(Class<T> clazz) throws RuntimeException;

    void setTimeOut(long timeout);

    long getTimeOut();
}
