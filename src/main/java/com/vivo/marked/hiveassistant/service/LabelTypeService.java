package com.vivo.marked.hiveassistant.service;

import com.vivo.marked.hiveassistant.entity.LabelTypeInfo;

import java.util.List;

/**
 * @BelongsProject: hive-assistant
 * @BelongsPackage: com.vivo.marked.hiveassistant.service
 * @Author: chenxiaoming
 * @CreateTime: 2018-09-21 15:21
 * @Description: 标签类别
 */
public interface LabelTypeService {

    /**
     * 查询所有标签类别信息
     * @return
     */
    List<LabelTypeInfo> findAll();
}
