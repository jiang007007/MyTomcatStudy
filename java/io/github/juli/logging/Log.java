package io.github.juli.logging;

/**
 * 日志的输出级别 6中
 * 1. trace
 * 2. debug
 * 3. info
 * 4. warn
 * 5. error
 * 6. fatal
 */
public interface Log {
    public boolean isDebugEnabled();

    public boolean isErrorEnabled();

    public boolean isFatalEnabled();

    public boolean isInfoEnabled();

    public boolean isTraceEnabled();

    public boolean isWarnEnabled();

    public void trace(Object msg);

    public void trace(Object msg, Throwable t);

    public void dubug(Object msg);

    public void dubug(Object msg, Throwable t);

    public void info(Object msg);

    public void info(Object msg, Throwable t);

    public void warn(Object msg);

    public void warn(Object msg, Throwable t);

    public void error(Object msg);

    public void error(Object msg, Throwable t);

    public void fatal(Object msg);

    public void fatal(Object msg, Throwable t);

}
