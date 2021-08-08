package com.mola.dataflows.core.handler.connect;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.mola.dataflows.core.listener.CommonBinlogListener;
import com.mola.dataflows.core.conf.ConnectionConfig;
import com.mola.dataflows.core.conf.MysqlConnectConfig;

import java.io.IOException;

/**
 * @author : molamola
 * @Project: DataFlows
 * @Description:
 * @date : 2021-08-08 18:07
 **/
public class BinlogConnectionHandler extends ConnectionHandler<BinaryLogClient>{

    /**
     * 监听器
     */
    private CommonBinlogListener binlogListener;

    public BinlogConnectionHandler(CommonBinlogListener binlogListener) {
        this.binlogListener = binlogListener;
    }

    @Override
    protected BinaryLogClient doConnect(ConnectionConfig config) {
        if (!(config instanceof MysqlConnectConfig)) {
            throw new RuntimeException("can not parse config");
        }
        MysqlConnectConfig connectConfig = (MysqlConnectConfig) config;
        // client
        BinaryLogClient client = new BinaryLogClient(connectConfig.getHost(),
                connectConfig.getPort(), connectConfig.getUsername(), connectConfig.getPassword());
        client.setServerId(connectConfig.getServerId());

        if (null == binlogListener) {
            throw new RuntimeException("listener can not be null");
        }
        client.registerEventListener(binlogListener);
        try {
            client.connect();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("connect failed, exception = " + e.getMessage());
        }
        return client;
    }
}
