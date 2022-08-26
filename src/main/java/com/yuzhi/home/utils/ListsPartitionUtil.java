//package com.yuzhi.home.utils;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.google.common.collect.Lists;
//import com.jimistore.boot.nemo.core.util.DateUtil;
//import com.jimistore.procuratorate.api.enums.IssueStatusEnum;
//import com.jimistore.procuratorate.api.enums.MarkStatusEnum;
//import com.jimistore.procuratorate.api.enums.ProcuratorateEventSourceEnum;
//import com.jimistore.procuratorate.api.request.ProcuratorateEventIssueAddRequest;
//import com.jimistore.procuratorate.api.request.ProcuratorateEventIssueListRequest;
//import com.jimistore.procuratorate.api.request.ProcuratorateEventIssueUpdateRequest;
//import com.jimistore.procuratorate.api.response.ProcuratorateEventIssueResponse;
//import com.jimistore.procuratorate.api.response.third.tianque.FindIssuePageResponse;
//import com.jimistore.procuratorate.api.service.IProcuratorateEventIssueService;
//import com.jimistore.procuratorate.api.utils.JsonUtil;
//import com.jimistore.procuratorate.dao.IProcuratorateEventIssueDao;
//import com.jimistore.procuratorate.entity.ProcuratorateEventIssue;
//import com.jimistore.procuratorate.utils.DateTimeUtil;
//import com.jimistore.procuratorate.utils.HttpUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.log4j.Logger;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.util.CollectionUtils;
//import org.springframework.validation.annotation.Validated;
//
//import javax.transaction.Transactional;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.TimeUnit;
//import java.util.stream.Collectors;
//
///**
// * @Description：检察院案件-impl
// * @Author：song
// * @Date：2022/07/04
// */
//@Slf4j
//@Service
//@Validated
////@Transactional
//public class ProcuratorateEventIssueService2 implements IProcuratorateEventIssueService {
//
//    private static Logger LOG = Logger.getLogger(ProcuratorateEventIssueService2.class);
//
//    ExecutorService executor = Executors.newFixedThreadPool(5);
//
//    @Autowired
//    private IProcuratorateEventIssueDao procuratorateEventIssueDao;
//
//    @Override
//    public List<ProcuratorateEventIssueResponse> list(ProcuratorateEventIssueListRequest request) {
//
//        return procuratorateEventIssueDao.list(request);
//    }
//
//    @Override
//    public void create(ProcuratorateEventIssueAddRequest request) {
//        ProcuratorateEventIssue ProcuratorateEventIssue = new ProcuratorateEventIssue();
//        BeanUtils.copyProperties(request, ProcuratorateEventIssue);
//        procuratorateEventIssueDao.create(ProcuratorateEventIssue);
//    }
//
//    @Override
//    @Transactional
//    public void update(ProcuratorateEventIssueUpdateRequest request) {
//        ProcuratorateEventIssue procuratorateEventIssue = procuratorateEventIssueDao.getById(request.getId());
//        if (procuratorateEventIssue == null || procuratorateEventIssue.getDeleted()) {
//            throw new RuntimeException("找不到数据信息");
//        }
//        procuratorateEventIssue.setIssueStatus(request.getIssueStatus());
//        procuratorateEventIssue.setParams(request.getParams());
//        procuratorateEventIssueDao.update(procuratorateEventIssue);
//    }
//
//    @Override
//    public void delete(String memberId) {
//
//    }
//
//    @Override
//    public ProcuratorateEventIssueResponse detail(String id) {
//        ProcuratorateEventIssue ProcuratorateEventIssue = procuratorateEventIssueDao.getById(id);
//        if (ProcuratorateEventIssue == null) {
//
//            return null;
//        }
//        ProcuratorateEventIssueResponse response = new ProcuratorateEventIssueResponse();
//        BeanUtils.copyProperties(ProcuratorateEventIssue, response);
//
//        return response;
//    }
//
//    /**
//     * 批量查询，使用测试数据
//     */
//    @Override
//    public void importHistory() {
//        log.info("importHistory start");
//
////        String url = "http://10.45.14.83/api/issue-business-server/issue/findIssuePage";
////
////        JSONObject hander = new JSONObject();
////        hander.put("auth", "bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnRfaWQiOiIzMzA3MDAiLCJjb2RlIjoyMDAsInJvbGVfY29kZXMiOltdLCJ1c2VyX25hbWUiOiJDbHhqYzAwMUBqaHNnIiwiYXV0aG9yaXRpZXMiOlsiMTM1NjUwODU1NDYxNzQyNTk4NSIsIjE0MjAyMzUzNzM5MDgzMzI1OTMiLCIxNDIwMjM1NzIyODcwMjMxMDQ3Il0sImNsaWVudF9pZCI6InVzZXJjZW50ZXIiLCJyb2xlX2lkcyI6WzEzNTY1MDg1NTQ2MTc0MjU5ODUsMTQyMDIzNTM3MzkwODMzMjU5MywxNDIwMjM1NzIyODcwMjMxMDQ3XSwiYWRtaW5pc3RyYXRvciI6ZmFsc2UsImdyYW50X3R5cGUiOiJwYXNzd29yZCIsInVzZXJfaWQiOiIxMDE0NjgwMjE2ODg0MjI0MDAxIiwib3JnX2lkIjoiMTM3MjAyMjAwNTQzMTMzNjk2MyIsInN1Y2Nlc3MiOnRydWUsInNjb3BlIjpbImFsbCJdLCJvYXV0aF9pZCI6IiIsImV4cCI6MTY1ODk1MDE3NSwianRpIjoiODYzMmRiYWYtYzA2MC00ODQ4LWE1OGItMTk3ODk5NTQyNGVkIn0.HD_G1wY9HUs-nP9F6tX7VtVBocRX383Yzj_kPiMsI_A");
////        hander.put("Authorization", "Basic dXNlcmNlbnRlcjoxMTg2MDQ1ZDU1OTlkZTZlZjJjYTI4MjM0N2E1NWNhMg==");
////        hander.put("Referer", "http://10.45.14.83/province-header/0.1.0/tq-zjgrid-issuecenter/areaEvent/eventListHave");
////        hander.put("Cookie", "-admin-token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnRfaWQiOiIzMzA3MDAiLCJjb2RlIjoyMDAsInJvbGVfY29kZXMiOltdLCJ1c2VyX25hbWUiOiJDbHhqYzAwMUBqaHNnIiwiYXV0aG9yaXRpZXMiOlsiMTM1NjUwODU1NDYxNzQyNTk4NSIsIjE0MjAyMzUzNzM5MDgzMzI1OTMiLCIxNDIwMjM1NzIyODcwMjMxMDQ3Il0sImNsaWVudF9pZCI6InVzZXJjZW50ZXIiLCJyb2xlX2lkcyI6WzEzNTY1MDg1NTQ2MTc0MjU5ODUsMTQyMDIzNTM3MzkwODMzMjU5MywxNDIwMjM1NzIyODcwMjMxMDQ3XSwiYWRtaW5pc3RyYXRvciI6ZmFsc2UsImdyYW50X3R5cGUiOiJwYXNzd29yZCIsInVzZXJfaWQiOiIxMDE0NjgwMjE2ODg0MjI0MDAxIiwib3JnX2lkIjoiMTM3MjAyMjAwNTQzMTMzNjk2MyIsInN1Y2Nlc3MiOnRydWUsInNjb3BlIjpbImFsbCJdLCJvYXV0aF9pZCI6IiIsImV4cCI6MTY1ODk1MDE3NSwianRpIjoiODYzMmRiYWYtYzA2MC00ODQ4LWE1OGItMTk3ODk5NTQyNGVkIn0.HD_G1wY9HUs-nP9F6tX7VtVBocRX383Yzj_kPiMsI_A");
////
////        JSONObject jsonObject = new JSONObject();
////        jsonObject.put("searchKeyWords", "");
////        jsonObject.put("issueMark", 6);
////        jsonObject.put("status", 300);
////        jsonObject.put("sidx", "last_deal_date");
////        jsonObject.put("sord", "desc");
////        jsonObject.put("rows", 10000);
////        jsonObject.put("page", 1);
////        jsonObject.put("orgId", "1372022184028995586");
////        jsonObject.put("departmentDimension", 1);
////
////        // 请求接口
////        String responseJson = HttpUtil.doPost(url, hander, jsonObject);
////        JSONObject jsonObject2 = JSONObject.parseObject(responseJson);
////        System.out.println(jsonObject2.toJSONString());
////        String rows = jsonObject2.getJSONObject("data").getString("rows");
//
//        String rows = JsonUtil.readJsonFile(JsonUtil.FILE_NAME_10000);
//
//        List<FindIssuePageResponse> responseList = new ArrayList<>(2000);
//        List<JSONObject> jsonObjects = JSONArray.parseArray(rows, JSONObject.class);
//        for (JSONObject jo : jsonObjects) {
//            String joStr = JSON.toJSONString(jo);
//            FindIssuePageResponse response = JSON.parseObject(joStr, FindIssuePageResponse.class);
//            response.setParams(joStr);
//            responseList.add(response);
//        }
//
//        if (!CollectionUtils.isEmpty(responseList)) {
//            CompletableFuture.runAsync(() -> {
//                List<String> serialNumberList = responseList.stream().map(FindIssuePageResponse::getSerialNumber).collect(Collectors.toList());
//                int total = serialNumberList.size();
//                // 每次批量取的数据
//                int batchCount = 2000;
//                // 向上取整
//                int count = (int) Math.ceil((double) total / batchCount);
//
//                List<String> allSerialNumberIdList = Lists.newArrayList();
//                for (int h = 1; h <= count; h++) {
//                    List<String> existSerialNumberIdList = procuratorateEventIssueDao.listExistSerialNumberId(serialNumberList, 1, batchCount);
//                    allSerialNumberIdList.addAll(existSerialNumberIdList);
//                }
//                if (!CollectionUtils.isEmpty(allSerialNumberIdList)) {
//                    serialNumberList = serialNumberList.stream().filter(item -> !allSerialNumberIdList.contains(item)).collect(Collectors.toList());
//                }
//
//                List<ProcuratorateEventIssue> issueList = Lists.newArrayList();
//                ProcuratorateEventIssue pojo = null;
//                for (FindIssuePageResponse pageResponse : responseList) {
//                    if (serialNumberList.contains(pageResponse.getSerialNumber())) {
//                        pojo = new ProcuratorateEventIssue();
//                        pojo.setSerialNumber(pageResponse.getSerialNumber());
//                        pojo.setParams(pageResponse.getParams());
//                        pojo.setCategoryCode(pageResponse.getCategoryCode());
//                        pojo.setCategoryCodeName(pageResponse.getCategoryCodeName());
//                        pojo.setIssueContent(pageResponse.getIssueContent());
//                        pojo.setSubject(pageResponse.getSubject());
//                        pojo.setSource(ProcuratorateEventSourceEnum.TIANQUE.getCode());
//                        pojo.setIssueStatus(IssueStatusEnum.ISSUE_END.getCode());
//                        pojo.setMarkStatus(MarkStatusEnum.UN_MARK.getCode());
//                        pojo.setIssueCreateDate(pageResponse.getCreateDate());
//                        pojo.setFullCreateOrgName(pageResponse.getFullCreateOrgName());
//                        issueList.add(pojo);
//                    }
//                }
//                procuratorateEventIssueDao.batchCreate(issueList);
//                log.info(String.format("importHistory all issue size: %s 条, add issue size: %s 条"), responseList.size(), issueList.size());
//            }, executor);
//        }
//
//        log.info("importHistory end");
//    }
//
//    /**
//     * 批量查询，5000条每次
//     */
//    // XXX: 目前用这个
//    @Override
//    public void importHistoryV3() {
//        int total = 470000; // 总记录数
//        int batchCount = 10000; // 每次批量取的数据
//        int count = (int) Math.ceil((double) total / batchCount); // 向上取整
//
//        String url = "http://10.45.14.83/api/issue-business-server/issue/findIssuePage";
//        JSONObject hander = new JSONObject();
//        hander.put("auth", "bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnRfaWQiOiIzMzA3MDAiLCJjb2RlIjoyMDAsInJvbGVfY29kZXMiOltdLCJ1c2VyX25hbWUiOiJDbHhqYzAwMUBqaHNnIiwiYXV0aG9yaXRpZXMiOlsiMTM1NjUwODU1NDYxNzQyNTk4NSIsIjE0MjAyMzUzNzM5MDgzMzI1OTMiLCIxNDIwMjM1NzIyODcwMjMxMDQ3Il0sImNsaWVudF9pZCI6InVzZXJjZW50ZXIiLCJyb2xlX2lkcyI6WzEzNTY1MDg1NTQ2MTc0MjU5ODUsMTQyMDIzNTM3MzkwODMzMjU5MywxNDIwMjM1NzIyODcwMjMxMDQ3XSwiYWRtaW5pc3RyYXRvciI6ZmFsc2UsImdyYW50X3R5cGUiOiJwYXNzd29yZCIsInVzZXJfaWQiOiIxMDE0NjgwMjE2ODg0MjI0MDAxIiwib3JnX2lkIjoiMTM3MjAyMjAwNTQzMTMzNjk2MyIsInN1Y2Nlc3MiOnRydWUsInNjb3BlIjpbImFsbCJdLCJvYXV0aF9pZCI6IiIsImV4cCI6MTY1ODk1MDE3NSwianRpIjoiODYzMmRiYWYtYzA2MC00ODQ4LWE1OGItMTk3ODk5NTQyNGVkIn0.HD_G1wY9HUs-nP9F6tX7VtVBocRX383Yzj_kPiMsI_A");
//        hander.put("Authorization", "Basic dXNlcmNlbnRlcjoxMTg2MDQ1ZDU1OTlkZTZlZjJjYTI4MjM0N2E1NWNhMg==");
//        hander.put("Referer", "http://10.45.14.83/province-header/0.1.0/tq-zjgrid-issuecenter/areaEvent/eventListHave");
//        hander.put("Cookie", "-admin-token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnRfaWQiOiIzMzA3MDAiLCJjb2RlIjoyMDAsInJvbGVfY29kZXMiOltdLCJ1c2VyX25hbWUiOiJDbHhqYzAwMUBqaHNnIiwiYXV0aG9yaXRpZXMiOlsiMTM1NjUwODU1NDYxNzQyNTk4NSIsIjE0MjAyMzUzNzM5MDgzMzI1OTMiLCIxNDIwMjM1NzIyODcwMjMxMDQ3Il0sImNsaWVudF9pZCI6InVzZXJjZW50ZXIiLCJyb2xlX2lkcyI6WzEzNTY1MDg1NTQ2MTc0MjU5ODUsMTQyMDIzNTM3MzkwODMzMjU5MywxNDIwMjM1NzIyODcwMjMxMDQ3XSwiYWRtaW5pc3RyYXRvciI6ZmFsc2UsImdyYW50X3R5cGUiOiJwYXNzd29yZCIsInVzZXJfaWQiOiIxMDE0NjgwMjE2ODg0MjI0MDAxIiwib3JnX2lkIjoiMTM3MjAyMjAwNTQzMTMzNjk2MyIsInN1Y2Nlc3MiOnRydWUsInNjb3BlIjpbImFsbCJdLCJvYXV0aF9pZCI6IiIsImV4cCI6MTY1ODk1MDE3NSwianRpIjoiODYzMmRiYWYtYzA2MC00ODQ4LWE1OGItMTk3ODk5NTQyNGVkIn0.HD_G1wY9HUs-nP9F6tX7VtVBocRX383Yzj_kPiMsI_A");
//
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("searchKeyWords", "");
//        jsonObject.put("issueMark", 6);
//        jsonObject.put("status", 300);
//        jsonObject.put("sidx", "last_deal_date");
//        jsonObject.put("sord", "desc");
//        jsonObject.put("rows", 10000);
//        jsonObject.put("page", 1);
//        jsonObject.put("orgId", "1372022184028995586");
//        jsonObject.put("departmentDimension", 1);
//        for (int h = 1; h <= count; h++) {
//            jsonObject.put("page", h);
//            // 请求接口
//            String responseJson = HttpUtil.doPost(url, hander, jsonObject);
//            JSONObject jsonObject2 = JSONObject.parseObject(responseJson);
//            String rows = jsonObject2.getJSONObject("data").getString("rows");
////            String rows = JsonUtil.readJsonFile(JsonUtil.FILE_NAME_10);
//
//            List<FindIssuePageResponse> responseList = new ArrayList<>(1000);
//            List<JSONObject> jsonObjects = JSONArray.parseArray(rows, JSONObject.class);
//            if (CollectionUtils.isEmpty(jsonObjects)) {
//                return;
//            }
//
//            for (JSONObject jo : jsonObjects) {
//                String joStr = JSON.toJSONString(jo);
//                FindIssuePageResponse response = JSON.parseObject(joStr, FindIssuePageResponse.class);
//                response.setParams(joStr);
//                responseList.add(response);
//            }
//
//            if (!CollectionUtils.isEmpty(responseList)) {
//                CompletableFuture.runAsync(() -> {
//                    List<ProcuratorateEventIssue> issueList = Lists.newArrayList();
//                    for (FindIssuePageResponse pageResponse : responseList) {
//                        ProcuratorateEventIssue pojo = new ProcuratorateEventIssue();
//                        pojo.setSerialNumber(pageResponse.getSerialNumber());
//                        pojo.setParams(pageResponse.getParams());
//                        pojo.setIssueStatus(IssueStatusEnum.ISSUE_END.getCode());
//                        pojo.setCategoryCode(pageResponse.getCategoryCode());
//                        pojo.setCategoryCodeName(pageResponse.getCategoryCodeName());
//                        pojo.setIssueContent(pageResponse.getIssueContent());
//                        pojo.setSubject(pageResponse.getSubject());
//                        pojo.setSource(ProcuratorateEventSourceEnum.TIANQUE.getCode());
//                        issueList.add(pojo);
//                    }
//                    procuratorateEventIssueDao.batchCreate(issueList);
//                }, executor);
//
//                log.info("importHistory, size: " + responseList.size());
//            }
//        }
//
//    }
//
//    /**
//     * 批量查询，线上接口
//     */
//    @Override
//    public void importHistoryV2() {
//        log.error("importHistory执行开始。。。");
//        String url = "http://10.45.14.83/api/issue-business-server/issue/findIssuePage";
//
//        JSONObject hander = new JSONObject();
//        hander.put("auth", "bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnRfaWQiOiIzMzA3MDAiLCJjb2RlIjoyMDAsInJvbGVfY29kZXMiOltdLCJ1c2VyX25hbWUiOiJDbHhqYzAwMUBqaHNnIiwiYXV0aG9yaXRpZXMiOlsiMTM1NjUwODU1NDYxNzQyNTk4NSIsIjE0MjAyMzUzNzM5MDgzMzI1OTMiLCIxNDIwMjM1NzIyODcwMjMxMDQ3Il0sImNsaWVudF9pZCI6InVzZXJjZW50ZXIiLCJyb2xlX2lkcyI6WzEzNTY1MDg1NTQ2MTc0MjU5ODUsMTQyMDIzNTM3MzkwODMzMjU5MywxNDIwMjM1NzIyODcwMjMxMDQ3XSwiYWRtaW5pc3RyYXRvciI6ZmFsc2UsImdyYW50X3R5cGUiOiJwYXNzd29yZCIsInVzZXJfaWQiOiIxMDE0NjgwMjE2ODg0MjI0MDAxIiwib3JnX2lkIjoiMTM3MjAyMjAwNTQzMTMzNjk2MyIsInN1Y2Nlc3MiOnRydWUsInNjb3BlIjpbImFsbCJdLCJvYXV0aF9pZCI6IiIsImV4cCI6MTY2MTI3MzQ3OSwianRpIjoiZDdkMTYwZjAtNzk0YS00NDU4LWI2OTktY2JlOTA3YTI2Mjc3In0.ziIqd29XlVCLJvn3mqqA0So8GsHTiCLYxd5XTPLDg4U");
//        hander.put("Authorization", "Basic dXNlcmNlbnRlcjoxMTg2MDQ1ZDU1OTlkZTZlZjJjYTI4MjM0N2E1NWNhMg==");
//        hander.put("Referer", "http://10.45.14.83/province-header/0.1.0/tq-zjgrid-issuecenter/areaEvent/eventListHave");
//        hander.put("Cookie", "-admin-token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnRfaWQiOiIzMzA3MDAiLCJjb2RlIjoyMDAsInJvbGVfY29kZXMiOltdLCJ1c2VyX25hbWUiOiJDbHhqYzAwMUBqaHNnIiwiYXV0aG9yaXRpZXMiOlsiMTM1NjUwODU1NDYxNzQyNTk4NSIsIjE0MjAyMzUzNzM5MDgzMzI1OTMiLCIxNDIwMjM1NzIyODcwMjMxMDQ3Il0sImNsaWVudF9pZCI6InVzZXJjZW50ZXIiLCJyb2xlX2lkcyI6WzEzNTY1MDg1NTQ2MTc0MjU5ODUsMTQyMDIzNTM3MzkwODMzMjU5MywxNDIwMjM1NzIyODcwMjMxMDQ3XSwiYWRtaW5pc3RyYXRvciI6ZmFsc2UsImdyYW50X3R5cGUiOiJwYXNzd29yZCIsInVzZXJfaWQiOiIxMDE0NjgwMjE2ODg0MjI0MDAxIiwib3JnX2lkIjoiMTM3MjAyMjAwNTQzMTMzNjk2MyIsInN1Y2Nlc3MiOnRydWUsInNjb3BlIjpbImFsbCJdLCJvYXV0aF9pZCI6IiIsImV4cCI6MTY2MTI3MzQ3OSwianRpIjoiZDdkMTYwZjAtNzk0YS00NDU4LWI2OTktY2JlOTA3YTI2Mjc3In0.ziIqd29XlVCLJvn3mqqA0So8GsHTiCLYxd5XTPLDg4U");
//
//        Date start = DateTimeUtil.strToDate("2022-01-01");
//        Date end = DateTimeUtil.strToDate("2023-01-01");
//        Date end2 = DateTimeUtil.strToDate("2022-12-31");
//
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("searchKeyWords", "");
//        jsonObject.put("issueMark", 6);
//        jsonObject.put("status", 300);
//        jsonObject.put("sidx", "last_deal_date");
//        jsonObject.put("sord", "desc");
//        jsonObject.put("rows", 10000);
//        jsonObject.put("page", 1);
//        jsonObject.put("orgId", "1372022184028995586");
//        jsonObject.put("departmentDimension", 1);
//
//        List<FindIssuePageResponse> responseList = new ArrayList<>(10000);
//        List<String> serialNumberList = new ArrayList<>(470000);
//
//        while (start.before(end)) {
////            Date endDate = DateTimeUtil.addDays(start, 1);
//            Date endDate = start;
//            String startTime = DateUtil.getDateStr(start, DateTimeUtil.YYYY_MM_DDHHMMSS);
//            String endTime = DateUtil.getDateStr(endDate, DateTimeUtil.YYYY_MM_DD235959);
//            if (endDate.after(end2)) {
//                endTime = DateUtil.getDateStr(end2, DateTimeUtil.YYYY_MM_DD235959);
//            }
//
////            jsonObject.put("lastDealDate", startTime);
////            jsonObject.put("endLastDealDate", endTime);
//            jsonObject.put("occurDate", startTime);
//            jsonObject.put("occurEndDate", endTime);
//
//            // 请求接口
//            String responseJson = HttpUtil.doPost(url, hander, jsonObject);
//            JSONObject jsonObject2 = JSONObject.parseObject(responseJson);
////            System.out.println(jsonObject2.toJSONString());
//            String rows = jsonObject2.getJSONObject("data").getString("rows");
//
//            List<JSONObject> jsonObjects = JSONArray.parseArray(rows, JSONObject.class);
//            for (JSONObject jo : jsonObjects) {
//                String joStr = JSON.toJSONString(jo);
//                FindIssuePageResponse response = JSON.parseObject(joStr, FindIssuePageResponse.class);
//                if (!serialNumberList.contains(response.getSerialNumber())) {
//                    response.setParams(joStr);
//                    responseList.add(response);
//                    serialNumberList.add(response.getSerialNumber());
//                }
//            }
//
//            log.error(String.format("批量查询基础平台，日期：%s ~ %s，该日条数：%s，总条数：%s", startTime, endTime, jsonObjects.size(), serialNumberList.size()));
//            start = DateTimeUtil.addDays(start, 1);
//        }
//
//        //切分
//        List<? extends List<FindIssuePageResponse>> lists = Lists.partition(responseList, 20000);
//
//        //多线程
//        ExecutorService service = Executors.newCachedThreadPool();
//        try {
//            for (List<FindIssuePageResponse> datas : lists) {
//                if (!CollectionUtils.isEmpty(datas)) {
//                    CompletableFuture.runAsync(() -> {
//                        for (FindIssuePageResponse pageResponse : datas) {
//                            ProcuratorateEventIssue pojo = new ProcuratorateEventIssue();
//                            pojo.setSerialNumber(pageResponse.getSerialNumber());
//                            pojo.setParams(pageResponse.getParams());
//                            pojo.setCategoryCode(pageResponse.getCategoryCode());
//                            pojo.setCategoryCodeName(pageResponse.getCategoryCodeName());
//                            pojo.setIssueContent(pageResponse.getIssueContent());
//                            pojo.setSubject(pageResponse.getSubject());
//                            pojo.setSource(ProcuratorateEventSourceEnum.TIANQUE.getCode());
//                            pojo.setIssueStatus(IssueStatusEnum.ISSUE_END.getCode());
//                            pojo.setMarkStatus(MarkStatusEnum.UN_MARK.getCode());
//                            pojo.setIssueCreateDate(pageResponse.getCreateDate());
//                            pojo.setFullCreateOrgName(pageResponse.getFullCreateOrgName());
//                            procuratorateEventIssueDao.create(pojo);
//                        }
//                    }, executor);
//                }
////                service.execute(() -> {
////                    //消费
////                    for (FindIssuePageResponse pageResponse : datas) {
////                        ProcuratorateEventIssue pojo = new ProcuratorateEventIssue();
////                        pojo.setSerialNumber(pageResponse.getSerialNumber());
////                        pojo.setParams(pageResponse.getParams());
////                        pojo.setCategoryCode(pageResponse.getCategoryCode());
////                        pojo.setCategoryCodeName(pageResponse.getCategoryCodeName());
////                        pojo.setIssueContent(pageResponse.getIssueContent());
////                        pojo.setSubject(pageResponse.getSubject());
////                        pojo.setSource(ProcuratorateEventSourceEnum.TIANQUE.getCode());
////                        pojo.setIssueStatus(IssueStatusEnum.ISSUE_END.getCode());
////                        pojo.setMarkStatus(MarkStatusEnum.UN_MARK.getCode());
////                        pojo.setIssueCreateDate(pageResponse.getCreateDate());
////                        pojo.setFullCreateOrgName(pageResponse.getFullCreateOrgName());
////                        procuratorateEventIssueDao.create(pojo);
////                    }
////                });
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error("importHistory执行失败");
//        } finally {
//            service.shutdown();
//            try {
//                service.awaitTermination(1, TimeUnit.DAYS);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        log.error("importHistory执行结束。。。");
//    }
//}
