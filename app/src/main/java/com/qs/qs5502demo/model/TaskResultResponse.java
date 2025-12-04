package com.qs.qs5502demo.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * AGV任务结果查询响应
 */
public class TaskResultResponse extends AgvResponse {
    @SerializedName("status")
    private String status;           // 任务状态：01等待执行、02执行中、08执行完成、09强制清空
    
    @SerializedName("points")
    private List<TaskResultPoint> points;  // 作业点对象列表
    
    @SerializedName("agvCode")
    private String agvCode;          // 执行该任务的AGV编号
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public List<TaskResultPoint> getPoints() {
        return points;
    }
    
    public void setPoints(List<TaskResultPoint> points) {
        this.points = points;
    }
    
    public String getAgvCode() {
        return agvCode;
    }
    
    public void setAgvCode(String agvCode) {
        this.agvCode = agvCode;
    }
    
    /**
     * 任务结果作业点对象
     */
    public static class TaskResultPoint {
        @SerializedName("sn")
        private String sn;
        
        @SerializedName("pointCode")
        private String pointCode;
        
        @SerializedName("pointType")
        private String pointType;
        
        @SerializedName("pointAction")
        private String pointAction;      // 作业动作集
        
        @SerializedName("pointStep")
        private String pointStep;        // 作业步骤集，步骤集与动作集相同时代表该作业点执行完成
        
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
        
        public String getPointAction() {
            return pointAction;
        }
        
        public void setPointAction(String pointAction) {
            this.pointAction = pointAction;
        }
        
        public String getPointStep() {
            return pointStep;
        }
        
        public void setPointStep(String pointStep) {
            this.pointStep = pointStep;
        }
    }
}

