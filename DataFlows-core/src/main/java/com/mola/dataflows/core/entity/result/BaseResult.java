package com.mola.dataflows.core.entity.result;

import lombok.Data;

import java.util.Map;

/**
 * @author : molamola
 * @Project: DataFlows
 * @Description: 基本结果类
 * @date : 2021-08-08 17:52
 **/
@Data
public class BaseResult<T> {

    /**
     * 是否成功
     */
    private boolean success;

    private long createTime = System.currentTimeMillis();

    /**
     * 消息
     */
    private String msg;

    private T data;

    private Map<String, Object> features;

    public static <T> BaseResult success(T data) {
        BaseResult<T> result = new BaseResult<>();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    public static <T> BaseResult error(String msg) {
        BaseResult<T> result = new BaseResult<>();
        result.setSuccess(false);
        result.setMsg(msg);
        return result;
    }
}
