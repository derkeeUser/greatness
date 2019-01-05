package com.vivo.marked.hiveassistant.service.impl;

import com.vivo.marked.hiveassistant.dao.ContentTypeDao;
import com.vivo.marked.hiveassistant.entity.ContentTypeInfo;
import com.vivo.marked.hiveassistant.service.ContentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @BelongsProject: hive-assistant
 * @BelongsPackage: com.vivo.marked.hiveassistant.service.impl
 * @Author: chenxiaoming
 * @CreateTime: 2018-09-20 11:59
 * @Description: ...
 */

@Service
public class ContentTypeServiceImpl implements ContentTypeService {

    @Autowired
    private ContentTypeDao contentTypeDao;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<ContentTypeInfo> findAll() {
        return contentTypeDao.findAll();
    }
}
