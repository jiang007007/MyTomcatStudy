package io.github.servlet;

public enum DispatcherType {
    //转发请求
    FORWARD,
    INCLUDE,

    //默认处理方式
    REQUEST,
    //异步
    ASYNC,
    //错误的处理方式
    ERROR
}
