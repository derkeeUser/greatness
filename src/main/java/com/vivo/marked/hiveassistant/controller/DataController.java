package com.vivo.marked.hiveassistant.controller;

import com.monitorjbl.xlsx.StreamingReader;
import com.vivo.marked.hiveassistant.common.CommonVO;
import com.vivo.marked.hiveassistant.common.Contains;
import com.vivo.marked.hiveassistant.common.DateUtil;
import com.vivo.marked.hiveassistant.common.PageHelper;
import com.vivo.marked.hiveassistant.entity.ContentTypeInfo;
import com.vivo.marked.hiveassistant.entity.LabelTypeInfo;
import com.vivo.marked.hiveassistant.entity.StatementInfo;
import com.vivo.marked.hiveassistant.entity.UserInfo;
import com.vivo.marked.hiveassistant.model.SummaryInfo;
import com.vivo.marked.hiveassistant.service.ContentTypeService;
import com.vivo.marked.hiveassistant.service.LabelTypeService;
import com.vivo.marked.hiveassistant.service.StatementService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: hive-assistant
 * @BelongsPackage: com.vivo.marked.hiveassistant.controller
 * @Author: chenxiaoming
 * @CreateTime: 2018-09-17 16:54
 * @Description: 数据增删改查操作
*/

@Controller
@RequestMapping("/data")
public class DataController {

    private final static Logger LOGGER = LoggerFactory.getLogger(DataController.class);

    @Autowired
    private StatementService statementService;

    @Autowired
    private ContentTypeService contentTypeService;

    @Autowired
    private LabelTypeService labelTypeService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 获取数据列表
     * @param page 当前页
     * @param limit 页面大小
     * @param content 语句
     * @param isProcessed 是否标注
     * @return
     */
    @PostMapping("/datalists.do")
    @ResponseBody
    public CommonVO dataList(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page
            , @RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit
            , @RequestParam(value = "content", required = false, defaultValue = "") String content
            , @RequestParam(value = "isProcessed", required = false, defaultValue = "") String isProcessed) {
        LOGGER.info("content：" + content);
        UserInfo user = (UserInfo)request.getSession().getAttribute(Contains.CURRUSER_SESSION);
        if("".equals(isProcessed)){
           isProcessed = null;
        }
        Integer isProcessedConvertInteger = null;
        if(isProcessed != null){
            isProcessedConvertInteger = Integer.valueOf(isProcessed);
        }
        try {
            //分页获取
            PageHelper<SummaryInfo> pages = statementService.findByContentLikeAndIsProcessed(page,limit,content,isProcessedConvertInteger,user);
            return CommonVO.success(pages.getDataList(),pages.getTotalCount());
        } catch (IllegalArgumentException e) {
            return CommonVO.badParams(e);
        }
    }

    /**
     * 标注数据
     * @param summaryInfo json数据对象
     * @return
     */
    @PostMapping("/save.do")
    @ResponseBody
    public CommonVO save(@RequestBody SummaryInfo summaryInfo) {
        LOGGER.info("summaryInfo---->{},{}",summaryInfo.getContentType(),summaryInfo.getLabelType());
        try {
            if(summaryInfo == null){
                throw new IllegalArgumentException("请求参数不合法，请检查后再操作");
            }
            if(summaryInfo.getContentType()==0 && summaryInfo.getLabelType() != 0){
                throw new IllegalArgumentException("请选择语句类型");
            }
            Integer isProcessed = Contains.NO;
            if(summaryInfo.getLabelType() != null && summaryInfo.getLabelType() != 0){
                isProcessed = Contains.YES;
            }
            StatementInfo statementInfo = new StatementInfo(summaryInfo.getId().toString(),summaryInfo.getContent(),
                    summaryInfo.getContentType(),summaryInfo.getLabelType(),summaryInfo.getContentNameExtend(),
                    summaryInfo.getErrorCorrection(),summaryInfo.getUserId(),isProcessed,"",DateUtil.getStringDate());
            statementService.save(statementInfo);
            return CommonVO.success();
        } catch (IllegalArgumentException e) {
            LOGGER.error("数据更改失败!",e);
            return CommonVO.badParams(e.getMessage());
        }
    }

    /**
     * 入库
     * @param file Excel文件
     * @return
     */
    @PostMapping("/upload.do")
    @ResponseBody
    public CommonVO upload(MultipartFile file) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Contains
                .CURRUSER_SESSION);
        InputStream inputStream = null;
        try {
            if (!"Y".equals(userInfo.getIsAdmin())) {
                throw new IllegalArgumentException("您无权限操作");
            }
            if (null == file) {
                throw new IllegalArgumentException("请选择文件");
            }
            inputStream = file.getInputStream();
            List<ContentTypeInfo> ctList = contentTypeService.findAll();
            Map<String, Integer> ctCache = new HashMap<String, Integer>();
            for (ContentTypeInfo cti : ctList) {
                ctCache.put(cti.getTypeEnglishName(), Integer.valueOf(cti.getId().toString()));
            }

            List<LabelTypeInfo> ltList = labelTypeService.findAll();
            Map<String, Integer> ltCache = new HashMap<String, Integer>();
            for (LabelTypeInfo lti : ltList) {
                ltCache.put(lti.getLabelEnglishName(), Integer.valueOf(lti.getId().toString()));
            }

            Workbook workbook = StreamingReader.builder()
                    .rowCacheSize(100)    // number of rows to keep in memory (defaults to 10)
                    .bufferSize(4096)     // buffer size to use when reading InputStream to file
                    // (defaults to 1024)
                    .open(inputStream);

            Sheet sheet = workbook.getSheetAt(0);

            int rowNo = 0;
            int result = 0;
            int repeatCount = 0;
            Cell tmpCell = null;
            List<StatementInfo> sList = new ArrayList<StatementInfo>();
            outLayer:
            for (Row row : sheet) {
                StatementInfo sInfo = new StatementInfo();
                // 表头
                if (rowNo == 0) {
                    String content = row.getCell(0).getStringCellValue();
                    if (StringUtils.isBlank(content) || !"content".equals(content)) {
                        return CommonVO.badParams("表格数据不合法");
                    }
                } else {
                    for (short j = 0; j < row.getLastCellNum(); j++) {
                        //----获得 content------------------------------------------------------------
                        tmpCell = row.getCell(j);
                        if (null == tmpCell) {
                            continue;
                        }
                        String content = tmpCell.getStringCellValue();
                        if (content == null || "".equals(content) || StringUtils.isBlank(content)) {
                            continue;
                        }
                        if(statementService.countByContent(content)>0){
                            repeatCount++;
                            continue outLayer;
                        }
                        sInfo.setContent(content);

                        //----获得 content_type--------------------------------------------------------
                        tmpCell = row.getCell(++j);
                        if(tmpCell!=null) {
                            String contentType = tmpCell.getStringCellValue();
                            if (contentType != null && !"".equals(contentType) && !StringUtils.isBlank(contentType) && contentType.trim().length()>0) {
                                sInfo.setContentType(ctCache.get(contentType));
                            }else{
                                sInfo.setContentType(Contains.NO);
                            }
                        }else{
                            sInfo.setContentType(Contains.NO);
                        }


                        //----获得 label_type----------------------------------------------------------
                        tmpCell = row.getCell(++j);
                        if (tmpCell != null) {
                            String labelType = tmpCell.getStringCellValue();
                            if (labelType != null && !"".equals(labelType) && !StringUtils.isBlank(labelType) && labelType.trim().length()>0) {
                                sInfo.setLabelType(ltCache.get(labelType));
                            }else{
                                sInfo.setLabelType(Contains.NO);
                            }
                        }else{
                            sInfo.setLabelType(Contains.NO);
                        }

                        //----获得 content_name_extend----------------------------------------------------------
                        tmpCell = row.getCell(++j);
                        if (tmpCell != null) {
                            String cne = tmpCell.getStringCellValue();
                            if (cne != null && !"".equals(cne) && !StringUtils.isBlank(cne) && cne.trim().length()>0) {
                                sInfo.setContentNameExtend(cne);
                            }else{
                                sInfo.setContentNameExtend("");
                            }
                        }else{
                            sInfo.setContentNameExtend("");
                        }

                        //----获得 error_correction----------------------------------------------------------
                        tmpCell = row.getCell(++j);
                        if (tmpCell != null) {
                            String errCorr = tmpCell.getStringCellValue();
                            if (errCorr != null && !"".equals(errCorr) && !StringUtils.isBlank(errCorr) && errCorr.trim().length()>0) {
                                sInfo.setErrorCorrection(errCorr);
                            }else{
                                sInfo.setErrorCorrection("");
                            }
                        }else{
                            sInfo.setErrorCorrection("");
                        }

                        //处理状况
                        sInfo.setIsProcessed(0);
                        //创建时间
                        sInfo.setCreateTime(DateUtil.getStringDate());
                        sInfo.setUserId("");
                        sInfo.setModifyTime("");
                        sList.add(sInfo);
                    }
                }
                rowNo++;
            }
            LOGGER.info("筛选重复数据：{} 条",repeatCount);
            statementService.insertByList(sList);
            result+=sList.size();
            return CommonVO.success(result, null);
        } catch (Exception e) {
            LOGGER.error("上传文件失败", e);
            return CommonVO.badParams(e.getMessage());
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOGGER.error("关闭流失败", e);
                }
            }
        }
    }

    /**
     * 获取分配信息
     * @return
     */
    @PostMapping("/doDistribution.do")
    @ResponseBody
    public CommonVO doDistribution(){
        try{
            SummaryInfo summaryInfo = statementService.getData();
            return CommonVO.success(summaryInfo,null);
        }catch (Exception e){
            LOGGER.error("获取分配信息过程中发生错误");
            return CommonVO.error(e);
        }
    }

    @PostMapping("/distribution.do")
    @ResponseBody
    public CommonVO distribution(@RequestParam(value = "userId", required = false)String userId
    ,@RequestParam(value = "ctVal", required = false)Integer ctVal
    ,@RequestParam(value = "fpRecords", required = false)Integer fpRecords){
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Contains
                .CURRUSER_SESSION);
        try {
            if(userInfo!=null && !"Y".equals(userInfo.getIsAdmin())){
                throw new IllegalArgumentException("您无权进行此操作");
            }
            if (userId == null || "".equals(userId)) {
                throw new IllegalArgumentException("请选择用户");
            }
            if (fpRecords == null || "".equals(fpRecords)) {
                throw new IllegalArgumentException("请输入分配数量");
            }
            return CommonVO.success(statementService.taskAllocation(userId,ctVal,fpRecords),null);
        }catch (IllegalArgumentException e){
            LOGGER.error("分配任务失败");
            return CommonVO.badParams(e.getMessage());
        }
    }
}
