package com.vivo.marked.hiveassistant.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @BelongsProject: hive-assistant
 * @BelongsPackage: com.vivo.marked.hiveassistant.entity
 * @Author: chenxiaoming
 * @CreateTime: 2018-09-20 11:37
 * @Description: 内容类别
 */
@Document(collection = "t_content_type_info")
public class ContentTypeInfo {

    @Field("_id")
    private Object id;
    @Field("type_name")
    private String typeName;
    @Field("type_english_name")
    private String typeEnglishName;
    @Field("create_time")
    private String createTime;
    @Field("modify_time")
    private String modifyTime;

    public ContentTypeInfo() {
    }

    public ContentTypeInfo(String typeName, String typeEnglishName, String createTime, String modifyTime) {
        this.typeName = typeName;
        this.typeEnglishName = typeEnglishName;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeEnglishName() {
        return typeEnglishName;
    }

    public void setTypeEnglishName(String typeEnglishName) {
        this.typeEnglishName = typeEnglishName;
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
