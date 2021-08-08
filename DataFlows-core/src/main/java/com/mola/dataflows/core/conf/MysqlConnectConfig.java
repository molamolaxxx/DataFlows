package com.mola.dataflows.core.conf;

import lombok.Data;

/**
 * @author : molamola
 * @Project: DataFlows
 * @Description:
 * @date : 2021-08-08 17:30
 **/
@Data
public class MysqlConnectConfig extends ConnectionConfig{

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用于binlog监听
     */
    private long serverId;
}
