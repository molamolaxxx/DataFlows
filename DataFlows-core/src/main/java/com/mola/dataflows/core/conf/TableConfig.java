package com.mola.dataflows.core.conf;

import lombok.Data;

/**
 * @author : molamola
 * @Project: DataFlows
 * @Description: 监听表的配置
 * @date : 2021-08-08 17:42
 **/
@Data
public class TableConfig {

    /**
     * 数据库名称
     */
    private String database;

    /**
     * 数据表名称
     */
    private String tableName;
}
