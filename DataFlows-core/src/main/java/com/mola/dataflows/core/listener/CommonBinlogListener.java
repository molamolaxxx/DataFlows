package com.mola.dataflows.core.listener;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.*;
import com.mola.dataflows.core.common.DataFlowContext;

/**
 * @author : molamola
 * @Project: DataFlows
 * @Description: 全局binlog监听器
 * @date : 2021-08-08 18:14
 **/
public class CommonBinlogListener implements BinaryLogClient.EventListener {

    private DataFlowContext context;

    public CommonBinlogListener(DataFlowContext context) {
        this.context = context;
    }

    @Override
    public void onEvent(Event event) {
        EventData data = event.getData();
        context.getAsyncBinlogHandler().handle(data);
    }
}
