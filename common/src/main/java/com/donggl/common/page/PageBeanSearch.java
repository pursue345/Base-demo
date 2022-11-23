package com.donggl.common.page;

import javax.validation.constraints.NotNull;

/**
 * @description: 分页请求
 * @author donggl
 * @date 2022/11/10 14:41
 * @version 1.0
 */
public class PageBeanSearch<T> {


    @NotNull(message = "当前页码不能为空")
    private int currentPage;

    @NotNull(message = "每页容量不能为空")
    private int pageSize;

    private T query;


    public int getCurrentPage() {
        return this.currentPage;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public T getQuery() {
        return this.query;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setQuery(T query) {
        this.query = query;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof PageBeanSearch)) {
            return false;
        } else {
            PageBeanSearch<?> other = (PageBeanSearch)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getCurrentPage() != other.getCurrentPage()) {
                return false;
            } else if (this.getPageSize() != other.getPageSize()) {
                return false;
            } else {
                Object thisQuery = this.getQuery();
                Object otherQuery = other.getQuery();
                if (thisQuery == null) {
                    return otherQuery == null;
                } else {
                    return thisQuery.equals(otherQuery);
                }
            }
        }
    }


    protected boolean canEqual(Object other) {
        return other instanceof PageBeanSearch;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = result * 59 + this.getCurrentPage();
        result = result * 59 + this.getPageSize();
        Object query = this.getQuery();
        result = result * 59 + (query == null ? 43 : query.hashCode());
        return result;
    }


    @Override
    public String toString() {
        return "PageBeanSearch(currentPage=" + this.getCurrentPage() + ", pageSize=" + this.getPageSize() + ", query=" + this.getQuery() + ")";
    }


}