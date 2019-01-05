package com.vivo.marked.hiveassistant.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @BelongsProject: hive-assistant
 * @BelongsPackage: com.vivo.marked.hiveassistant.entity
 * @Author: chenxiaoming
 * @CreateTime: 2018-09-20 11:36
 * @Description: 标注数据
 */
@Document(collection = "t_statement_info")
public class StatementInfo {

    @Id
    @Field("_id")
    protected String id;
    @Field("content")
    protected String content;
    @Field("content_type")
    protected Integer contentType;
    @Field("label_type")
    protected Integer labelType;
    @Field("content_name_extend")
    protected String contentNameExtend;
    @Field("error_correction")
    protected String errorCorrection;
    @Field("user_id")
    protected String userId;
    @Field("is_processed")
    protected Integer isProcessed;
    @Field("create_time")
    protected String createTime;
    @Field("modify_time")
    protected String modifyTime;

    public StatementInfo() {

    }

    public StatementInfo(String id, String content, Integer contentType, Integer labelType, String contentNameExtend, String errorCorrection,
                         String userId, Integer isProcessed, String createTime, String modifyTime) {
        this.id = id;
        this.content = content;
        this.contentType = contentType;
        this.labelType = labelType;
        this.contentNameExtend = contentNameExtend;
        this.errorCorrection = errorCorrection;
        this.userId = userId;
        this.isProcessed = isProcessed;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    public StatementInfo(String content, Integer contentType, Integer labelType, String contentNameExtend, String errorCorrection, String userId,
                         Integer isProcessed, String createTime, String modifyTime) {
        this.content = content;
        this.contentType = contentType;
        this.labelType = labelType;
        this.contentNameExtend = contentNameExtend;
        this.errorCorrection = errorCorrection;
        this.userId = userId;
        this.isProcessed = isProcessed;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public Integer getLabelType() {
        return labelType;
    }

    public void setLabelType(Integer labelType) {
        this.labelType = labelType;
    }

    public String getContentNameExtend() {
        return contentNameExtend;
    }

    public void setContentNameExtend(String contentNameExtend) {
        this.contentNameExtend = contentNameExtend;
    }

    public String getErrorCorrection() {
        return errorCorrection;
    }

    public void setErrorCorrection(String errorCorrection) {
        this.errorCorrection = errorCorrection;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getIsProcessed() {
        return isProcessed;
    }

    public void setIsProcessed(Integer isProcessed) {
        this.isProcessed = isProcessed;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}
