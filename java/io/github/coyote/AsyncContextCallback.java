package io.github.coyote;

public interface AsyncContextCallback {

    void fireOnComplete();

    boolean isAvailable();

    void incrementInProgressAsyncCount();

    void decrementInProgressAsyncCount();
}
