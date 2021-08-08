package com.mola.dataflows.core.constants;

/**
 * 连接类型
 * 1、mysql mysql的连接
 * 2、redis
 * 3、net 网络连接，用于将监听的内容输送到网络
 * 4、client 将内容输送到客户端
 */
public enum ConnectionTypeEnum {
    MYSQL,REDIS,NET,CLIENT
}
