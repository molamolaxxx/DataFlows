package com.mola.dataflows.core.handler.binlog;

import com.github.shyiko.mysql.binlog.event.*;
import com.mola.dataflows.core.common.DataFlowContext;
import com.mola.dataflows.core.constants.ChangeTypeEnum;
import com.mola.dataflows.core.handler.DataFlowsHandler;
import com.mola.dataflows.core.listener.BinlogChangeListener;
import com.mola.dataflows.core.register.MetaInfo;
import com.mola.dataflows.core.register.MetaInfoRegister;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : molamola
 * @Project: DataFlows
 * @Description: binlog异步处理器
 * @date : 2021-08-08 21:23
 **/
@Slf4j
public class AsyncBinlogHandler implements DataFlowsHandler<EventData, Future> {

    private DataFlowContext context;

    private MetaInfoRegister metaInfoRegister;

    public AsyncBinlogHandler(DataFlowContext context) {
        this.context = context;
        this.metaInfoRegister = context.getMetaInfoRegister();
    }

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            10, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(20),
            new ThreadFactory() {
                AtomicInteger idx = new AtomicInteger();
                @Override
                public Thread newThread(Runnable runnable) {
                    Thread thread = new Thread("async-binlog-handler-" + idx.getAndIncrement());
                    thread.setDaemon(true);
                    return thread;
                }
            }
    );

    @Override
    public Future handle(EventData event) {
        return threadPoolExecutor.submit(new EventDataTask(event));
    }

    /**
     * binlog处理任务
     */
    public class EventDataTask implements Runnable {
        private EventData event;

        public EventDataTask(EventData event)  {
            this.event = event;
        }
        @Override
        public void run() {
            // 提交到线程池处理
            if (event instanceof TableMapEventData) {
                // 注册维护id和表的关系
                handleEvent((TableMapEventData) event);
            }
            if (event instanceof UpdateRowsEventData) {
                handleEvent((UpdateRowsEventData) event);
            } else if (event instanceof WriteRowsEventData) {
                handleEvent((WriteRowsEventData) event);
            } else if (event instanceof DeleteRowsEventData) {
                handleEvent((DeleteRowsEventData) event);
            }
        }
    }

    private void handleEvent(TableMapEventData data) {
        // 1.注册关联信息
        metaInfoRegister.register(data.getTableId(), data.getDatabase(), data.getTable());
    }

    private void handleEvent(UpdateRowsEventData data) {
        List<MetaInfo> metaInfos = metaInfoRegister.getMetaByTableId(data.getTableId());
        handleCommon(metaInfos, ChangeTypeEnum.UPDATE, data);
    }

    private void handleEvent(WriteRowsEventData data) {
        List<MetaInfo> metaInfos = metaInfoRegister.getMetaByTableId(data.getTableId());
        handleCommon(metaInfos, ChangeTypeEnum.INSERT, data);
    }

    private void handleEvent(DeleteRowsEventData data) {
        List<MetaInfo> metaInfos = metaInfoRegister.getMetaByTableId(data.getTableId());
        handleCommon(metaInfos, ChangeTypeEnum.DELETE, data);
    }

    private void handleCommon(List<MetaInfo> metaInfos, ChangeTypeEnum changeTypeEnum, EventData eventData){
        metaInfos.forEach(
                metaInfo -> {
                    List<BinlogChangeListener> listeners = metaInfo.getBinlogChangeListener();
                    if (null == listeners) {
                        return;
                    }
                    for (BinlogChangeListener listener : listeners) {
                        if (listener.supportType().contains(changeTypeEnum)) {
                            listener.onChange(eventData);
                        }
                    }
                }
        );
    }
}
