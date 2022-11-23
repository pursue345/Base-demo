package com.donggl.common.page;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 分页响应类
 * @author donggl
 * @date 2022/11/10 14:42
 * @version 1.0
 */
public class Response<T> implements Serializable {

    private static final long serialVersionUID = 6323923218194260827L;

    /**
     * 表格渲染值
     */
    private List<T> list;

    /**
     * 分页信息
     */
    private Pagination pagination = new Pagination();


    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
        this.pagination = new Pagination();
        pagination.setTotal(Long.valueOf(list.size()));
        pagination.setCurrent(1L);
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    /**
     * Setter for property 'pagination'.
     *
     * @param total 总数
     * @param page  当前页
     */
    public void setPagination(Long total, Long page) {
        this.pagination.setTotal(total);
        this.pagination.setCurrent(page);
    }
}

