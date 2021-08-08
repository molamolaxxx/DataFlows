package com.mola.dataflows.core.register;

import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * @author : molamola
 * @Project: DataFlows
 * @Description: 基础信息注册管理
 * @date : 2021-08-08 21:42
 **/
public class MetaInfoRegister {

    /**
     * database-table -> meta
     */
    private Map<String, List<MetaInfo>> databaseTableMetaMap = Maps.newConcurrentMap();

    /**
     * id -> MetaInfo
     */
    private Map<String, MetaInfo> metaInfoMap = Maps.newConcurrentMap();

    /**
     * tableid -> database-table
     */
    private Map<Long, String> tableIdMetaMap = Maps.newConcurrentMap();

    public void register(Long tableId, String database, String tableName) {
        if (null == database || null == tableName || null == tableId) {
            throw new RuntimeException("key value can not be null");
        }
        tableIdMetaMap.put(tableId, getKey(database, tableName));
    }

    public List<MetaInfo> getMetaByTableId(Long tableId) {
        String k1 = tableIdMetaMap.get(tableId);
        return null == k1 ? null : databaseTableMetaMap.get(k1);
    }

    private String getKey(String database, String tableName) {
        return database + "-" + tableName;
    }
}
