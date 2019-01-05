package com.vivo.marked.hiveassistant.service;

import com.vivo.marked.hiveassistant.common.PageHelper;
import com.vivo.marked.hiveassistant.entity.StatementInfo;
import com.vivo.marked.hiveassistant.entity.UserInfo;
import com.vivo.marked.hiveassistant.model.SummaryInfo;

import java.util.List;

/**
 * @BelongsProject: hive-assistant
 * @BelongsPackage: com.vivo.marked.hiveassistant.service
 * @Author: chenxiaoming
 * @CreateTime: 2018-09-20 14:41
 * @Description: ...
 */
public interface StatementService {

    PageHelper<SummaryInfo> findByContentLikeAndIsProcessed(int page, int limit, String content, Integer isProcessed, UserInfo user);

    SummaryInfo getData();

    void save(StatementInfo statementInfo);

    /**
     * 导入数据
     * @param statementInfoList
     */
    void insertByList(List<StatementInfo> statementInfoList);

    /**
     * 判重
     * @param content
     * @return
     */
    int countByContent(String content);

    /**
     * 分配任务
     * @param userId
     * @param ctVal
     * @param fpRecords
     * @return
     */
    int taskAllocation(String userId,Integer ctVal,Integer fpRecords);
}
