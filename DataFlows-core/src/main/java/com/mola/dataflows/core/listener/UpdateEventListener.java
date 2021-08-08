package com.mola.dataflows.core.listener;

import com.github.shyiko.mysql.binlog.event.UpdateRowsEventData;

/**
 * @author : molamola
 * @Project: DataFlows
 * @Description: 更新时的监听器
 * @date : 2021-08-08 20:41
 **/
public class UpdateEventListener implements BinlogChangeListener<UpdateRowsEventData> {

    @Override
    public void onChange(UpdateRowsEventData event) {
        
    }
}
