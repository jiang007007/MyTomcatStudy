package io.github.servlet;

import java.io.IOException;
import java.util.EventListener;

/**
 * 异步监听 (监听事件)
 */
public interface AsyncListener extends EventListener {

    void onComplete(AsyncEvent event) throws IOException;

    void onTimeOut(AsyncEvent event) throws IOException;

    void onError(AsyncEvent event) throws IOException;

    void onStartAsync(AsyncEvent event) throws IOException;
}
