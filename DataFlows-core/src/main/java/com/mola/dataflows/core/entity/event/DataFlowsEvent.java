package com.mola.dataflows.core.entity.event;

import lombok.Data;

import java.util.Map;

/**
 * @author : molamola
 * @Project: DataFlows
 * @Description: 事件
 * @date : 2021-08-08 17:40
 **/
@Data
public class DataFlowsEvent<T> {

    /**
     * 参数
     */
    private T param;

    private long startTime = System.currentTimeMillis();

    /**
     * 事件类型
     */
    private String type;

    /**
     * 额外参数
     */
    private Map<String, Object> data;
}
