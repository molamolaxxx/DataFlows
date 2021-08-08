package com.mola.dataflows.core.handler.connect;

import com.mola.dataflows.core.conf.ConnectionConfig;
import com.mola.dataflows.core.entity.event.ConnectionEvent;
import com.mola.dataflows.core.entity.result.BaseResult;
import com.mola.dataflows.core.handler.DataFlowsHandler;

/**
 * @author : molamola
 * @Project: DataFlows
 * @Description:
 * @date : 2021-08-08 17:49
 **/
public abstract class ConnectionHandler<R> implements DataFlowsHandler<ConnectionEvent, BaseResult<R>> {

    @Override
    public BaseResult<R> handle(ConnectionEvent event) {
        R result = null;
        try {
            result = doConnect(event.getParam());
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.error(e.getMessage());
        }
        return BaseResult.success(result);
    }

    /**
     * 处理连接
     * @param config
     * @return
     */
    protected abstract R doConnect(ConnectionConfig config);
}
