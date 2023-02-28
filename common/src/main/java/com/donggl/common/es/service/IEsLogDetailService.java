package com.donggl.common.es.service;

/**
 * @ClassName IEsLogDetailService
 * @Description TODO
 * @Author donggl
 * @Date 2022/12/2 10:24
 * @Version 1.0
 */
public interface IEsLogDetailService {

    /**
     * @description  根据id加载指定信息到索引
     * @author  donggl
     * @date    2022/12/2 10:29
     * @param	logId
     * @return  void
     */
    void loadLogInfoById(Long logId);

    /**
     * @description: 创建索引结构，可覆盖
     * @author donggl
     * @date 2022/12/2 11:07
     * @version 1.0
     */
    void createIndex(String esDocEntityName,Boolean isOverride);
}
