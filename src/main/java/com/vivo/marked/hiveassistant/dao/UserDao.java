package com.vivo.marked.hiveassistant.dao;

import com.vivo.marked.hiveassistant.entity.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @BelongsProject: hive-assistant
 * @BelongsPackage: com.vivo.marked.hiveassistant.dao
 * @Author: chenxiaoming
 * @CreateTime: 2018-09-14 10:22
 * @Description: ...
 */
public interface UserDao extends MongoRepository<UserInfo, String> {

    Page<UserInfo> findByLoginNameLikeAndIdNot(String loginName,Object id, Pageable pageable);

    Page<UserInfo> findByLoginNameLikeAndId(String loginName, Object id, Pageable pageable);

    int countByLoginNameLike(String loginName);

    int countByLoginNameLikeAndId(String loginName, Object id);
}
