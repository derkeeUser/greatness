package com.vivo.marked.hiveassistant.dao;

import com.vivo.marked.hiveassistant.entity.StatementInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @BelongsProject: hive-assistant
 * @BelongsPackage: com.vivo.marked.hiveassistant.dao
 * @Author: chenxiaoming
 * @CreateTime: 2018-09-20 14:23
 * @Description: ...
 */
public interface StatementDao  extends MongoRepository<StatementInfo,String> {

    Page<StatementInfo> findByContentLike(String content, Pageable pageable);

    Page<StatementInfo> findByContentLikeAndUserId(String content,String userId, Pageable pageable);

    int countByContent(String content);
}
