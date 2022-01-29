package io.github.core;

import io.github.coyote.AsyncContextCallback;
import io.github.servlet.AsyncContext;
import io.github.servlet.AsyncListener;
import io.github.servlet.ServletRequest;
import io.github.servlet.ServletResponse;

public class AsyncContextImpl implements AsyncContext, AsyncContextCallback {



    @Override
    public void fireOnComplete() {

    }

    @Override
    public boolean isAvailable() {
        return false;
    }

    @Override
    public void incrementInProgressAsyncCount() {

    }

    @Override
    public void decrementInProgressAsyncCount() {

    }

    @Override
    public void complete() {

    }

    @Override
    public void start(Runnable run) {

    }

    @Override
    public void addListener(AsyncListener listener, ServletRequest request, ServletResponse response) {

    }

    @Override
    public <T extends AsyncListener> T createListener(Class<T> clazz) throws RuntimeException {
        return null;
    }

    @Override
    public void setTimeOut(long timeout) {

    }

    @Override
    public long getTimeOut() {
        return 0;
    }
}
