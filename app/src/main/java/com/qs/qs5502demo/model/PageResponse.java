package com.qs.qs5502demo.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * 分页响应
 */
public class PageResponse<T> {
    @SerializedName("list")
    private List<T> list;
    
    @SerializedName("total")
    private int total;
    
    @SerializedName("pageNum")
    private int pageNum;
    
    @SerializedName("pageSize")
    private int pageSize;
    
    public List<T> getList() {
        return list;
    }
    
    public void setList(List<T> list) {
        this.list = list;
    }
    
    public int getTotal() {
        return total;
    }
    
    public void setTotal(int total) {
        this.total = total;
    }
    
    public int getPageNum() {
        return pageNum;
    }
    
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
    
    public int getPageSize() {
        return pageSize;
    }
    
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}

