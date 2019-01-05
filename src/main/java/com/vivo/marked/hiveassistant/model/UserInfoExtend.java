package com.vivo.marked.hiveassistant.model;

import com.vivo.marked.hiveassistant.entity.UserInfo;

/**
 * @BelongsProject: hive-assistant
 * @BelongsPackage: com.vivo.marked.hiveassistant.model
 * @Author: chenxiaoming
 * @CreateTime: 2018-09-30 15:21
 * @Description: 用户类扩展
 */
public class UserInfoExtend extends UserInfo {

    /**
     * 已分配数(个)
     */
    private long allocatedNum;

    /**
     * 已处理数(个)
     */
    private long processed;

    /**
     * 未处理数(个)
     */
    private long unProcessed;

    public UserInfoExtend() {

    }

    public UserInfoExtend(UserInfo userInfo) {
        super(userInfo);
    }

    public UserInfoExtend(long allocatedNum, long processed, long unProcessed) {
        this.allocatedNum = allocatedNum;
        this.processed = processed;
        this.unProcessed = unProcessed;
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
