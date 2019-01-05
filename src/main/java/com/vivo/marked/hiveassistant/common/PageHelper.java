package com.vivo.marked.hiveassistant.common;

import java.util.List;

/**
 * @BelongsProject: hive-assistant
 * @BelongsPackage: com.vivo.marked.hiveassistant.common
 * @Author: chenxiaoming
 * @CreateTime: 2018-09-28 19:42
 * @Description: 分页工具
 */
public class PageHelper<T> {
    private int totalCount;

    private List<T> dataList;

    public PageHelper() {
    }

    public PageHelper(int totalCount, List<T> dataList) {
        this.totalCount = totalCount;
        this.dataList = dataList;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}
