package com.vivo.marked.hiveassistant.dao;

import com.mongodb.internal.validator.UpdateFieldNameValidator;
import com.vivo.marked.hiveassistant.entity.LabelTypeInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @BelongsProject: hive-assistant
 * @BelongsPackage: com.vivo.marked.hiveassistant.dao
 * @Author: chenxiaoming
 * @CreateTime: 2018-09-20 14:22
 * @Description: ...
 */
public interface LabelTypeDao extends MongoRepository<LabelTypeInfo,String> {

}
