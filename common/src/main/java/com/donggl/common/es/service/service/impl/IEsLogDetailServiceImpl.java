//package com.donggl.common.es.service.service.impl;
//
//import cn.hutool.core.bean.BeanUtil;
//import cn.hutool.core.util.StrUtil;
//import com.donggl.common.config.thread.ThreadExecutorConfig;
//import com.donggl.common.entity.LogChild;
//import com.donggl.common.entity.LogDetail;
//import com.donggl.common.es.entity.EsLogChild;
//import com.donggl.common.es.entity.EsLogDetail;
//import com.donggl.common.es.mapper.EsLogDetailRepository;
//import com.donggl.common.es.service.IEsLogDetailService;
//import com.donggl.common.log.LoggerUtil;
//import com.donggl.common.service.ILogChildService;
//import com.donggl.common.service.ILogDetailService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
//import org.springframework.data.elasticsearch.core.IndexOperations;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
///**
// * @ClassName IEsLogDetailServiceImpl
// * @Description TODO
// * @Author donggl
// * @Date 2022/12/2 10:25
// * @Version 1.0
// */
//@Service
//public class IEsLogDetailServiceImpl implements IEsLogDetailService {
//
//    private static final Logger logger = LoggerFactory.getLogger(IEsLogDetailServiceImpl.class);
//
//    @Resource
//    private ILogDetailService logDetailService;
//    @Resource
//    private ILogChildService iLogChildService;
//    @Resource
//    private EsLogDetailRepository esLogDetailRepository;
//
//    @Resource
//    private ElasticsearchRestTemplate elasticsearchRestTemplate;
//
//    @Override
//    public void loadLogInfoById(Long logId) {
//        EsLogDetail esLogDetail = new EsLogDetail();
//        LogDetail logDetail = logDetailService.getById(logId);
//        BeanUtil.copyProperties(logDetail, esLogDetail);
//        List<LogChild> logChildList = iLogChildService.list();
//        ArrayList<EsLogChild> esLogChildArrayList = new ArrayList<>();
//        logChildList.forEach(e -> {
//            EsLogChild esLogChild = new EsLogChild();
//            BeanUtils.copyProperties(e, esLogChild);
//            esLogChildArrayList.add(esLogChild);
//        });
//        esLogDetail.setEsLogChildList(esLogChildArrayList);
//        saveLogDetatilInfo(esLogDetail);
//    }
//
//    @Override
//    public void createIndex(String esDocEntityName, Boolean isOverride) {
//        if (StrUtil.isBlank(esDocEntityName)) {
//            LoggerUtil.info(logger, "只能创建指定名称的文档索引:{},{}", esDocEntityName, isOverride);
//        }
//        IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(EsLogDetail.class);
//        //删除索引
//        indexOperations.delete();
//        //创建新的索引
//        indexOperations.create();
//        indexOperations.refresh();
//        indexOperations.putMapping(indexOperations.createMapping());
//    }
//
//    /**
//     * @description: 写入数据到es
//     * @author donggl
//     * @date 2022/12/2 11:08
//     * @version 1.0
//     */
//    private void saveLogDetatilInfo(EsLogDetail esLogDetail) {
//        esLogDetailRepository.save(esLogDetail);
//    }
//
//
//}
