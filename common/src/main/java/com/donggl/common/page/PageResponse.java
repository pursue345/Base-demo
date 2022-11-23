package com.donggl.common.page;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;

/**
 * @description: 分页返回数据封装
 * @author donggl
 * @date 2022/11/10 14:42
 * @version 1.0
 */
public class PageResponse<T> extends Response<T> implements Serializable {

    private static final long serialVersionUID = 6323923218194260827L;

    public void setList(IPage<T> page) {
        super.setList(page.getRecords());
        getPagination().setTotal(page.getTotal());
        getPagination().setCurrent(page.getCurrent());
    }

    public PageResponse() {
    }
}
