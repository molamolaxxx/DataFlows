package com.mola.dataflows.core.conf;

import com.mola.dataflows.core.constants.ConnectionTypeEnum;
import lombok.Data;

/**
 * @author : molamola
 * @Project: DataFlows
 * @Description: 连接实例
 * @date : 2021-08-08 17:21
 **/
@Data
public class ConnectionConfig {

    /**
     * 连接类型
     */
    private ConnectionTypeEnum typeEnum;

    /**
     * 主机地址
     */
    private String host;

    /**
     * 端口号
     */
    private int port;
}
