//package com.donggl.common.es.controller;
//
//import cn.hutool.core.bean.BeanUtil;
//import com.alibaba.fastjson.JSONObject;
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.donggl.common.config.datasource.aop.DataSourceType;
//import com.donggl.common.config.datasource.aop.TargetDataSource;
//import com.donggl.common.entity.LogChild;
//import com.donggl.common.entity.LogDetail;
//import com.donggl.common.es.entity.EsLogChild;
//import com.donggl.common.es.entity.EsLogDetail;
//import com.donggl.common.service.ILogChildService;
//import com.donggl.common.service.ILogDetailService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @ClassName EsController
// * @Description TODO
// * @Author donggl
// * @Date 2022/12/2 14:22
// * @Version 1.0
// */
//@RestController
//@RequestMapping("/es")
//@TargetDataSource(value = DataSourceType.MYSQL_DATASOURCE1)
//public class EsController {
//
//    private static final Logger logger = LoggerFactory.getLogger("LOG_DETAIL_ES_LOG");
//
//    @Resource
//    private ILogDetailService logDetailService;
//
//    @Resource
//    private ILogChildService logChildService;
//
//    @RequestMapping("/insertEs")
//    @ResponseBody
//    public String insertEs() {
//        List<LogDetail> list = logDetailService.list();
//        list.forEach(e->{
//            EsLogDetail esLogDetail = new EsLogDetail();
//            BeanUtil.copyProperties(e,esLogDetail);
//
//            LambdaQueryWrapper<LogChild> logchildWapper = new LambdaQueryWrapper<>();
//            logchildWapper.eq(LogChild::getRecordId,e.getId());
//            List<LogChild> logChildList = logChildService.list(logchildWapper);
//            List<EsLogChild> esLogChildArrayList = new ArrayList<>();
//            logChildList.forEach(e1->{
//                EsLogChild esLogChild = new EsLogChild();
//                BeanUtil.copyProperties(e1,esLogChild);
//                esLogChildArrayList.add(esLogChild);
//            });
//            esLogDetail.setEsLogChildList(esLogChildArrayList);
//            esLogDetail.setTimestamp(System.currentTimeMillis());
//            logger.info(JSONObject.toJSONString(esLogDetail));
//        });
//        return "";
//    }
//
//}
