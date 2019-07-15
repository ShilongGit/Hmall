package com.itheima.bean;

import java.util.List;

public class PageBean<T> {
    //当前页面
    private int currentPage;
    //页面个数
    private int pageSize;
    //总记录数
    private long totalCount;
    //总页数
    private int totalPage;
    //分页查询到的数据
    private List<T> list;


    public PageBean() {
    }

    public PageBean(int currentPage, int pageSize, long totalCount, int totalPage, List<T> list) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.list = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
