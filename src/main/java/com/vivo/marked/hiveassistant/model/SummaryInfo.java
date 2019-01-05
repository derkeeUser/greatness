package com.vivo.marked.hiveassistant.model;

import com.vivo.marked.hiveassistant.entity.ContentTypeInfo;
import com.vivo.marked.hiveassistant.entity.LabelTypeInfo;
import com.vivo.marked.hiveassistant.entity.StatementInfo;
import com.vivo.marked.hiveassistant.entity.UserInfo;

import java.util.List;

/**
 * @BelongsProject: hive-assistant
 * @BelongsPackage: com.vivo.marked.hiveassistant.model
 * @Author: chenxiaoming
 * @CreateTime: 2018-09-26 12:43
 * @Description: 综合信息表
 */
public class SummaryInfo extends StatementInfo {

    /**
     * 内容类别列表
     */
    private List<ContentTypeInfo> contentTypeInfo;

    /**
     * 标签类别列表
     */
    private List<LabelTypeInfo> labelTypeInfo;

    /**
     * 用户列表
     */
    private List<UserInfo> userInfoList;

    /**
     * 总记录数
     */
    private long totalCount;

    /**
     * 已分配数
     */
    private long allocatedNum;

    /**
     * 已处理数
     */
    private long processed;

    /**
     * 未处理数
     */
    private long unProcessed;

    public SummaryInfo() {

    }

    public SummaryInfo(List<ContentTypeInfo> contentTypeInfo, List<LabelTypeInfo> labelTypeInfo, StatementInfo statementInfo) {
        this.contentTypeInfo = contentTypeInfo;
        this.labelTypeInfo = labelTypeInfo;
        this.id = statementInfo.getId();
        this.content = statementInfo.getContent();
        this.contentType = statementInfo.getContentType();
        this.labelType = statementInfo.getLabelType();
        this.contentNameExtend = statementInfo.getContentNameExtend();
        this.errorCorrection = statementInfo.getErrorCorrection();
        this.userId = statementInfo.getUserId();
    }

    public SummaryInfo(List<ContentTypeInfo> contentTypeInfo, List<LabelTypeInfo> labelTypeInfo, List<UserInfo> userInfoList, long totalCount, long
            allocatedNum, long processed, long unProcessed) {
        this.contentTypeInfo = contentTypeInfo;
        this.labelTypeInfo = labelTypeInfo;
        this.userInfoList = userInfoList;
        this.totalCount = totalCount;
        this.allocatedNum = allocatedNum;
        this.processed = processed;
        this.unProcessed = unProcessed;
    }

    public static SummaryInfo getSummaryInfo(List<ContentTypeInfo> contentTypeInfo, List<LabelTypeInfo> labelTypeInfo, StatementInfo statementInfo) {
        return new SummaryInfo(contentTypeInfo, labelTypeInfo, statementInfo);
    }

    public List<ContentTypeInfo> getContentTypeInfo() {
        return contentTypeInfo;
    }

    public void setContentTypeInfo(List<ContentTypeInfo> contentTypeInfo) {
        this.contentTypeInfo = contentTypeInfo;
    }

    public List<LabelTypeInfo> getLabelTypeInfo() {
        return labelTypeInfo;
    }

    public void setLabelTypeInfo(List<LabelTypeInfo> labelTypeInfo) {
        this.labelTypeInfo = labelTypeInfo;
    }

    public List<UserInfo> getUserInfoList() {
        return userInfoList;
    }

    public void setUserInfoList(List<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getAllocatedNum() {
        return allocatedNum;
    }

    public void setAllocatedNum(long allocatedNum) {
        this.allocatedNum = allocatedNum;
    }

    public long getProcessed() {
        return processed;
    }

    public void setProcessed(long processed) {
        this.processed = processed;
    }

    public long getUnProcessed() {
        return unProcessed;
    }

    public void setUnProcessed(long unProcessed) {
        this.unProcessed = unProcessed;
    }
}
