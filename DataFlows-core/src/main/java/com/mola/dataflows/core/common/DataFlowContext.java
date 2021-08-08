package com.mola.dataflows.core.common;

import com.mola.dataflows.core.handler.binlog.AsyncBinlogHandler;
import com.mola.dataflows.core.handler.connect.BinlogConnectionHandler;
import com.mola.dataflows.core.listener.CommonBinlogListener;
import com.mola.dataflows.core.register.MetaInfoRegister;
import lombok.Data;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author : molamola
 * @Project: DataFlows
 * @Description: 上下文
 * @date : 2021-08-08 21:37
 **/
@Data
public class DataFlowContext {

    /**
     * 异步提交binlog
     */
    private AsyncBinlogHandler asyncBinlogHandler;

    /**
     * binlog监听器
     */
    private CommonBinlogListener commonBinlogListener;

    /**
     * binlog连接器
     */
    private BinlogConnectionHandler binlogConnectionHandler;

    /**
     * 基本信息注册器
     */
    private MetaInfoRegister metaInfoRegister;

    private AtomicBoolean flag= new AtomicBoolean(false);

    public void init() {
        if (flag.get()) {
            return;
        }
        this.metaInfoRegister = new MetaInfoRegister();
        this.commonBinlogListener = new CommonBinlogListener(this);
        this.binlogConnectionHandler = new BinlogConnectionHandler(this.commonBinlogListener);
        this.asyncBinlogHandler = new AsyncBinlogHandler(this);
        flag.compareAndSet(false, true);
    }
}
