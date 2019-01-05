package com.vivo.marked.hiveassistant.dao;

import com.vivo.marked.hiveassistant.entity.ContentTypeInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @BelongsProject: hive-assistant
 * @BelongsPackage: com.vivo.marked.hiveassistant.dao
 * @Author: chenxiaoming
 * @CreateTime: 2018-09-20 11:53
 * @Description: ...
 */
public interface ContentTypeDao extends MongoRepository<ContentTypeInfo, String> {

}
