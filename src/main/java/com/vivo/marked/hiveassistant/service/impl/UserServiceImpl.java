package com.vivo.marked.hiveassistant.service.impl;

import com.vivo.marked.hiveassistant.common.Contains;
import com.vivo.marked.hiveassistant.dao.UserDao;
import com.vivo.marked.hiveassistant.entity.StatementInfo;
import com.vivo.marked.hiveassistant.entity.UserInfo;
import com.vivo.marked.hiveassistant.model.UserInfoExtend;
import com.vivo.marked.hiveassistant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: hive-assistant
 * @BelongsPackage: com.vivo.marked.hiveassistant.service.impl
 * @Author: chenxiaoming
 * @CreateTime: 2018-09-14 10:31
 * @Description: ...
 */

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDao userDao;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public UserInfo findUserByLoginName(String loginName, String passWord) {
        Query query = new Query(Criteria.where("loginName").is(loginName));
        UserInfo user = mongoTemplate.findOne(query, UserInfo.class);
        String encryPassword = DigestUtils.md5Hex(loginName + DigestUtils.md5Hex(passWord));
        if (user != null && encryPassword.equals(user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public void regist(UserInfo userInfo) {
        //加密
        userInfo.setPassword(DigestUtils.md5Hex(userInfo.getLoginName() + DigestUtils.md5Hex(userInfo.getPassword())));
        mongoTemplate.insert(userInfo);
    }

    @Override
    public List<UserInfoExtend> findAllByloginName(String loginName, int page, int size, UserInfo userInfo) {
        List<UserInfoExtend> uieList = new ArrayList<UserInfoExtend>();
        List<UserInfo> userInfoList = null;
        UserInfoExtend uie = null;
        Query query = null;
        if ("Y".equals(userInfo.getIsAdmin())) {
            //管理员查看所有用户，移除当前管理员信息
            userInfoList = userDao.findByLoginNameLikeAndIdNot(loginName, userInfo.getId(), PageRequest.of(page, size)).getContent();
        } else {
            userInfoList = userDao.findByLoginNameLikeAndId(loginName, userInfo.getId(), PageRequest.of(page, size)).getContent();
        }
        //获取每个用户的已分配数据量、已处理数、未处理数
        for (UserInfo user : userInfoList) {
            query = new Query();
            uie = new UserInfoExtend(user);
            //已分配数据量
            query.addCriteria(Criteria.where("userId").is(user.getId().toString()));
            uie.setAllocatedNum(mongoTemplate.count(query, StatementInfo.class));
            //已处理数
            query.addCriteria(Criteria.where("isProcessed").is(Contains.YES));
            uie.setProcessed(mongoTemplate.count(query, StatementInfo.class));
            //未处理数
            query = new Query();
            query.addCriteria(Criteria.where("userId").is(user.getId().toString()));
            query.addCriteria(Criteria.where("isProcessed").is(Contains.NO));
            uie.setUnProcessed(mongoTemplate.count(query, StatementInfo.class));
            uieList.add(uie);
        }
        return uieList;
    }

    @Override
    public int countByLoginNameLike(String loginName, UserInfo userInfo) {
        if ("Y".equals(userInfo.getIsAdmin())) {
            //管理员查看所有用户,减去当前登录管理员数
            return userDao.countByLoginNameLike(loginName) - 1;
        }
        return userDao.countByLoginNameLikeAndId(loginName, userInfo.getId());
    }
}
