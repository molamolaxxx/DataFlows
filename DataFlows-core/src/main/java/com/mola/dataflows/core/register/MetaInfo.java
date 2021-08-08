package com.mola.dataflows.core.register;

import com.mola.dataflows.core.conf.ConnectionConfig;
import com.mola.dataflows.core.conf.MysqlConnectConfig;
import com.mola.dataflows.core.conf.TableConfig;
import com.mola.dataflows.core.listener.BinlogChangeListener;
import lombok.Data;

import java.util.List;

/**
 * @author : molamola
 * @Project: DataFlows
 * @Description:注册元信息
 * @date : 2021-08-08 20:49
 **/
@Data
public class MetaInfo {

    /**
     * 唯一的id
     */
    private String id;

    /**
     * 监听mysql的config
     */
    private MysqlConnectConfig fromDataConfig;

    /**
     * 监听表的config
     */
    private TableConfig tableConfig;

    /**
     * 出口连接
     */
    private ConnectionConfig toDataConfig;

    /**
     * cid
     */
    private String cid;

    /**
     * 注册名
     */
    private String name;

    /**
     * 注册信息描述
     */
    private String desc;

    /**
     * 注册创建时间
     */
    private long createTime = System.currentTimeMillis();

    /**
     * 监听器组，脚本会自动生成
     */
    private List<BinlogChangeListener> binlogChangeListener;
}
