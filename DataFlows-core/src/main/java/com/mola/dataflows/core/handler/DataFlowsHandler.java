package com.mola.dataflows.core.handler;

/**
 * 基础事件处理器
 */
public interface DataFlowsHandler<T, R> {

    /**
     * 处理事件
     * @param event
     * @return
     */
    R handle(T event);
}
