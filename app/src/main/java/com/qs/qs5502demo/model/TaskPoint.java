package com.qs.qs5502demo.model;

import com.google.gson.annotations.SerializedName;

/**
 * AGV任务作业点对象
 */
public class TaskPoint {
    @SerializedName("sn")
    private String sn;              // 作业序号
    
    @SerializedName("pointCode")
    private String pointCode;       // 作业编号
    
    @SerializedName("pointType")
    private String pointType;       // 作业类型，02通用(包括取货)、04放货
    
    @SerializedName("matCode")
    private String matCode;         // 取放货物料信息（可选）
    
    public TaskPoint() {
    }
    
    public TaskPoint(String sn, String pointCode, String pointType) {
        this.sn = sn;
        this.pointCode = pointCode;
        this.pointType = pointType;
    }
    
    public TaskPoint(String sn, String pointCode, String pointType, String matCode) {
        this.sn = sn;
        this.pointCode = pointCode;
        this.pointType = pointType;
        this.matCode = matCode;
    }
    
    public String getSn() {
        return sn;
    }
    
    public void setSn(String sn) {
        this.sn = sn;
    }
    
    public String getPointCode() {
        return pointCode;
    }
    
    public void setPointCode(String pointCode) {
        this.pointCode = pointCode;
    }
    
    public String getPointType() {
        return pointType;
    }
    
    public void setPointType(String pointType) {
        this.pointType = pointType;
    }
    
    public String getMatCode() {
        return matCode;
    }
    
    public void setMatCode(String matCode) {
        this.matCode = matCode;
    }
}

