package com.mola.dataflows.core.register;

import lombok.Data;

/**
 * @author : molamola
 * @Project: DataFlows
 * @Description: groovy脚本对应的注册元，会自动生成listener
 * @date : 2021-08-08 20:58
 **/
@Data
public class ScriptMetaInfo extends MetaInfo{

    /**
     * 处理监听的脚本
     */
    String script;
}
