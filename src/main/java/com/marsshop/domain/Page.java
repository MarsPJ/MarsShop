package com.marsshop.domain;

import java.io.Serializable;

/**
 * 分页
 */
public class Page implements Serializable {
    // 当前页码
    private Integer pageIndex;
    // 当前页面的第一条记录的位置，从0开始记录（不是商品id号）
    private Integer firstIndex;
    // 一页展示的数量
    private Integer pageSize;
    // 总记录数
    private Integer count;
    // 总页数
    private Integer totalPage;
    // 上一页页码
    private Integer prevPageIndex;
    // 下一页页码
    private Integer nextPageIndex;

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;

        // this.prevPageIndex = this.pageIndex - 1;
        // if (this.prevPageIndex < 1) {
        //     this.prevPageIndex = 1;
        // }
        //
        // this.nextPageIndex = this.pageIndex + 1;
        // if (this.nextPageIndex > this.totalPage) {
        //     this.nextPageIndex = this.totalPage;
        // }
    }

    public Integer getFirstIndex() {
        return firstIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCount() {
        return count;
    }

    //每次setcount都相当于更新一次page的其它信息
    public void setCount(Integer count) {
        this.count = count;
        this.totalPage = (int) Math.ceil((double) this.count / this.pageSize);
        // 上一页
        this.prevPageIndex = this.pageIndex - 1;
        if (this.prevPageIndex < 1) {
            this.prevPageIndex = 1;
        }
        // 下一页
        this.nextPageIndex = this.pageIndex + 1;
        if (this.nextPageIndex > this.totalPage) {
            this.nextPageIndex = this.totalPage;
        }
        // 当前页的第一条记录号
        this.firstIndex = (this.pageIndex - 1) * this.pageSize;
    }

    public Page(Integer pageIndex) {
        this.pageIndex = pageIndex;
        // 默认数量最大为5
        this.pageSize = 5;
    }

    public Page(Integer pageIndex, Integer pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public Integer getPrevPageIndex() {
        return prevPageIndex;
    }


    public Integer getNextPageIndex() {
        return nextPageIndex;
    }

}
