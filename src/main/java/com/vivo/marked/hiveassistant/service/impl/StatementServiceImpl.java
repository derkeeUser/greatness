package com.vivo.marked.hiveassistant.service.impl;

import com.mongodb.client.result.UpdateResult;
import com.vivo.marked.hiveassistant.common.Contains;
import com.vivo.marked.hiveassistant.common.PageHelper;
import com.vivo.marked.hiveassistant.dao.ContentTypeDao;
import com.vivo.marked.hiveassistant.dao.LabelTypeDao;
import com.vivo.marked.hiveassistant.dao.StatementDao;
import com.vivo.marked.hiveassistant.entity.ContentTypeInfo;
import com.vivo.marked.hiveassistant.entity.LabelTypeInfo;
import com.vivo.marked.hiveassistant.entity.StatementInfo;
import com.vivo.marked.hiveassistant.entity.UserInfo;
import com.vivo.marked.hiveassistant.model.SummaryInfo;
import com.vivo.marked.hiveassistant.service.StatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: hive-assistant
 * @BelongsPackage: com.vivo.marked.hiveassistant.service.impl
 * @Author: chenxiaoming
 * @CreateTime: 2018-09-20 14:42
 * @Description: ...
 */
@Service
public class StatementServiceImpl implements StatementService {

    @Autowired
    private StatementDao statementDao;

    @Autowired
    private ContentTypeDao contentTypeDao;

    @Autowired
    private LabelTypeDao labelTypeDao;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public PageHelper<SummaryInfo> findByContentLikeAndIsProcessed(int page, int limit, String content, Integer isProcessed, UserInfo user) {
        Query query = new Query();
        query.addCriteria(Criteria.where("content").regex(".*?\\" +content+ ".*"));

        if (isProcessed != null) {
            query.addCriteria(Criteria.where("isProcessed").is(isProcessed));
        }

        if(user!=null && !"Y".equals(user.getIsAdmin())){
            query.addCriteria(Criteria.where("userId").is(user.getId().toString()));
        }

        //获取记录数
        long totalCount = mongoTemplate.count(query,StatementInfo.class);

        //分页
        query.skip((page-1)*limit);
        query.limit(limit);

        List<StatementInfo> statementInfos = mongoTemplate.find(query,StatementInfo.class);

        //语句类型列表
        List<ContentTypeInfo> contentTypeInfoList = contentTypeDao.findAll();
        //标签类型列表
        List<LabelTypeInfo> labelTypeInfoList = labelTypeDao.findAll();
        List<SummaryInfo> summaryInfoList = new ArrayList<SummaryInfo>();

        for(StatementInfo sInfo : statementInfos){
            summaryInfoList.add(SummaryInfo.getSummaryInfo(contentTypeInfoList,labelTypeInfoList,sInfo));
        }

        return new PageHelper<SummaryInfo>((int) totalCount,summaryInfoList);

    }

    @Override
    public SummaryInfo getData() {
        SummaryInfo summaryInfo = new SummaryInfo();

        //获取语句类别信息列表
        summaryInfo.setContentTypeInfo(contentTypeDao.findAll());

        //获取数据总记录数
        summaryInfo.setTotalCount(statementDao.count());

        Query query = new Query();
        query.addCriteria(Criteria.where("userId").ne(""));

        //获取已分配数(总)
        summaryInfo.setAllocatedNum(mongoTemplate.count(query,StatementInfo.class));

        //获取已处理数(总)
        query.addCriteria(Criteria.where("isProcessed").is(Contains.YES));
        summaryInfo.setProcessed(mongoTemplate.count(query,StatementInfo.class));

        query = new Query();
        query.addCriteria(Criteria.where("userId").ne(""));
        query.addCriteria(Criteria.where("isProcessed").is(Contains.NO));

        //获取未处理数(总)
        summaryInfo.setUnProcessed(mongoTemplate.count(query,StatementInfo.class));

        //获取用户列表
        query = new Query();
        query.addCriteria(Criteria.where("isAdmin").is("N"));
        summaryInfo.setUserInfoList(mongoTemplate.find(query,UserInfo.class));

        return summaryInfo;
    }

    @Override
    public void save(StatementInfo statementInfo) {
        Query query = Query.query(Criteria.where("id").is(statementInfo.getId()));
        Update  update = Update.update("contentType", statementInfo.getContentType())
                .set("labelType", statementInfo.getLabelType())
                .set("contentNameExtend",statementInfo.getContentNameExtend())
                .set("errorCorrection", statementInfo.getErrorCorrection())
                .set("isProcessed", statementInfo.getIsProcessed())
                .set("modifyTime", statementInfo.getModifyTime());
        mongoTemplate.updateMulti(query, update, StatementInfo.class);
    }

    @Override
    public void insertByList(List<StatementInfo> statementInfoList) {
        mongoTemplate.insertAll(statementInfoList);
    }

    @Override
    public int countByContent(String content) {
        return statementDao.countByContent(content);
    }


    @Override
    public int taskAllocation(String userId, Integer ctVal, Integer fpRecords) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(""));
        if(ctVal !=null && !"".equals(ctVal)){
            query.addCriteria(Criteria.where("contentType").is(ctVal));
        }

        long resultCount = 0;
        for (int i = 0; i < fpRecords; i++) {
            Update update = Update.update("userId",userId);
            UpdateResult result = mongoTemplate.updateFirst(query, update, StatementInfo.class);
            if(result != null && result.getMatchedCount()>0){
                resultCount +=  result.getMatchedCount();
                continue;
            }
            break;

            /*WriteResult result = mongoTemplate.updateFirst(query, update, StatementInfo.class);
            if(result != null && result.getN()>0){
                resultCount +=  result.getN();
                continue;
            }
            break;*/
        }

        if(resultCount == 0){
            throw new IllegalArgumentException("该语句类型没有匹配的数据");
        }else{
            return (int)resultCount;
        }
    }
}
