package com.vivo.marked.hiveassistant.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @BelongsProject: hive-assistant
 * @BelongsPackage: com.vivo.marked.hiveassistant.entity
 * @Author: chenxiaoming
 * @CreateTime: 2018-09-20 11:36
 * @Description: 标签类别
 */
@Document(collection = "t_label_type_info")
public class LabelTypeInfo {

    @Field("_id")
    private Object id;
    @Field("label_name")
    private String labelName;
    @Field("label_english_name")
    private String labelEnglishName;
    @Field("create_time")
    private String createTime;
    @Field("modify_time")
    private String modifyTime;

    public LabelTypeInfo() {
    }

    public LabelTypeInfo(String labelName, String labelEnglishName, String createTime, String modifyTime) {
        this.labelName = labelName;
        this.labelEnglishName = labelEnglishName;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getLabelEnglishName() {
        return labelEnglishName;
    }

    public void setLabelEnglishName(String labelEnglishName) {
        this.labelEnglishName = labelEnglishName;
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
