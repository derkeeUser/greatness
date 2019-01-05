package com.vivo.marked.hiveassistant.service.impl;

import com.vivo.marked.hiveassistant.dao.LabelTypeDao;
import com.vivo.marked.hiveassistant.entity.LabelTypeInfo;
import com.vivo.marked.hiveassistant.service.LabelTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @BelongsProject: hive-assistant
 * @BelongsPackage: com.vivo.marked.hiveassistant.service.impl
 * @Author: chenxiaoming
 * @CreateTime: 2018-09-21 15:22
 * @Description: ...
 */
@Service
public class LabelTypeServiceImpl implements LabelTypeService {

    @Autowired
    private LabelTypeDao labelTypeDao;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<LabelTypeInfo> findAll() {
        return labelTypeDao.findAll();
    }
}
