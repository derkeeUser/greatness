package com.vivo.marked.hiveassistant.service;

import com.vivo.marked.hiveassistant.entity.UserInfo;
import com.vivo.marked.hiveassistant.model.UserInfoExtend;

import java.util.List;

/**
 * @BelongsProject: hive-assistant
 * @BelongsPackage: com.vivo.marked.hiveassistant.service
 * @Author: chenxiaoming
 * @CreateTime: 2018-09-14 10:29
 * @Description: ...
 */
public interface UserService {

    List<UserInfoExtend> findAllByloginName(String loginName, int page, int size, UserInfo userInfo);

    int countByLoginNameLike(String loginName,UserInfo userInfo);

    /**
     * 登录
     * @param loginName
     * @param passWord
     * @return
     */
    UserInfo findUserByLoginName(String loginName,String passWord);

    /**
     * 注册
     * @param userInfo
     */
    void regist(UserInfo userInfo);
}
