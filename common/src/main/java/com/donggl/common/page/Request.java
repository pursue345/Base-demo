package com.donggl.common.page;

import java.io.Serializable;

/**
 * @description: 分页请求类
 * @author donggl
 * @date 2022/11/10 14:42
 * @version 1.0
 */
public class Request implements Serializable {

    private static final long serialVersionUID = -3783512820125403682L;

    /**
     * 当前页
     */
    private Integer currentPage = 1;

    /**
     * 每页条数
     */

    private Integer pageSize = 10;

    /**
     * 排序字段
     */
    private String sorter = "";

    /**
     * 获取当前页
     */

    public Integer getCurrentPage() {
        if (currentPage == null) {
            return 0;
        }
        return currentPage;
    }

    /**
     * 获取每页数量， 默认1000
     */

    public Integer getPageSize() {
        if (null == pageSize) {
            return 1000;
        }
        return pageSize;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSorter() {
        return sorter;
    }

    public void setSorter(String sorter) {
        this.sorter = sorter;
    }
}
