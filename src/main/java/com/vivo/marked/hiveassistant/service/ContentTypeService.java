package com.vivo.marked.hiveassistant.service;

import com.vivo.marked.hiveassistant.entity.ContentTypeInfo;

import java.util.List;

/**
 * @BelongsProject: hive-assistant
 * @BelongsPackage: com.vivo.marked.hiveassistant.service
 * @Author: chenxiaoming
 * @CreateTime: 2018-09-20 12:32
 * @Description: 内容类别
 */
public interface ContentTypeService {

    /**
     * 查询所有内容类别信息
     * @return
     */
    List<ContentTypeInfo> findAll();
}
