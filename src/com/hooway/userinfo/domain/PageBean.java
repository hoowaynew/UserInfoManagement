package com.hooway.userinfo.domain;

import java.util.List;

/**
 * 用于封装分页后的页面内容
 * @param <T>
 */
public class PageBean<T> {
    private int totalCount;  // 数据库总记录数
    private int currentPage; // 当前页面
    private int rows;    // 当前页面条目数
    private int pageCount;   // 分页的页面总数
    private List<T> list;   // 当前页面数据集合

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount='" + totalCount + '\'' +
                ", currentPage='" + currentPage + '\'' +
                ", rows='" + rows + '\'' +
                ", pageCount='" + pageCount + '\'' +
                ", list=" + list +
                '}';
    }
}
