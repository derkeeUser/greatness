package com.vivo.marked.hiveassistant.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @BelongsProject: hive-assistant
 * @BelongsPackage: com.vivo.marked.hiveassistant.entity
 * @Author: chenxiaoming
 * @CreateTime: 2018-09-14 10:31
 * @Description: 用户类
 */
@Document(collection = "t_user_info")
public class UserInfo {

    @Field("_id")
    protected Object id;
    @Field("login_name")
    protected String loginName;
    @Field("email")
    protected String email;
    @Field("password")
    protected String password;
    @Field("is_admin")
    protected String isAdmin;
    @Field("create_time")
    protected String createTime;

    public UserInfo() {
    }

    public UserInfo(Object id, String loginName, String email, String password, String isAdmin, String createTime) {
        this.id = id;
        this.loginName = loginName;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.createTime = createTime;
    }

    public UserInfo(UserInfo userInfo) {
        this.id = userInfo.getId();
        this.loginName = userInfo.getLoginName();
        this.email = userInfo.getEmail();
        this.password = userInfo.getPassword();
        this.isAdmin = userInfo.getIsAdmin();
        this.createTime = userInfo.getCreateTime();
    }

    public UserInfo(String loginName, String email, String password, String isAdmin, String createTime) {
        this.loginName = loginName;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.createTime = createTime;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}