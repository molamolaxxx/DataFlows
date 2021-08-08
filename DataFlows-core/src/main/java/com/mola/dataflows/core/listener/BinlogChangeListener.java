package com.mola.dataflows.core.listener;

import com.github.shyiko.mysql.binlog.event.EventData;
import com.google.common.collect.Sets;
import com.mola.dataflows.core.constants.ChangeTypeEnum;

import java.util.Set;

/**
 * binlog改变时的监听器
 */
public interface BinlogChangeListener<T extends EventData> {

    /**
     * 变化时调用
     * @param event
     */
    void onChange(T event);

    default Set<ChangeTypeEnum> supportType(){
        return Sets.newHashSet(ChangeTypeEnum.DELETE, ChangeTypeEnum.INSERT, ChangeTypeEnum.UPDATE);
    }
}
