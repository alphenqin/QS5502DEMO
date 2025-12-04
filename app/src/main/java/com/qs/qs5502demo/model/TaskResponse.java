package com.qs.qs5502demo.model;

import com.google.gson.annotations.SerializedName;

/**
 * 任务创建响应
 */
public class TaskResponse {
    @SerializedName("outID")
    private String outID;
    
    @SerializedName("status")
    private String status;
    
    public String getOutID() {
        return outID;
    }
    
    public void setOutID(String outID) {
        this.outID = outID;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
}

