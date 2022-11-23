package com.donggl.common.page;

import java.io.Serializable;

/**
 * @description: 分页
 * @author donggl
 * @date 2022/11/10 14:42
 * @version 1.0
 */
public class Pagination implements Serializable {

    private static final long serialVersionUID = -9122189141426414394L;
    /**
     * 数据总记录数
     */
    private Long total = 0L;

    /**
     * 当前页
     */
    private Long current = 0L;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getCurrent() {
        return current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }
}
