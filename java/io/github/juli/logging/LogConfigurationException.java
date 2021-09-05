package io.github.juli.logging;

public class LogConfigurationException extends RuntimeException {

    public LogConfigurationException() {
        super();
    }

    public LogConfigurationException(String msg) {
        super(msg);
    }

    public LogConfigurationException(Throwable cause) {
        super(cause);
    }

    public LogConfigurationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
