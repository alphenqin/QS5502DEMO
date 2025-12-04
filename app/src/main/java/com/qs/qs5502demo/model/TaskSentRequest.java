package com.qs.qs5502demo.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * AGV发送任务请求
 */
public class TaskSentRequest {
    @SerializedName("taskCode")
    private String taskCode;        // 调度系统任务编号（可选）
    
    @SerializedName("type")
    private String type;             // 任务类型，必填：01取放货任务、05充电、10急停、12解除急停、13清空指定outID任务、18解除待命
    
    @SerializedName("IsOrder")
    private String IsOrder;          // 是否有序（可选）
    
    @SerializedName("agvRange")
    private String agvRange;        // AGV范围（可选）
    
    @SerializedName("points")
    private List<TaskPoint> points;  // 作业点对象列表（取放货任务必填）
    
    @SerializedName("level")
    private String level;            // 任务级别，1优先任务，2普通任务(默认)
    
    @SerializedName("clearOutID")
    private String clearOutID;       // 被清空的外部业务id（清空指定任务时为必填）
    
    @SerializedName("outID")
    private String outID;            // 外部业务id
    
    public String getTaskCode() {
        return taskCode;
    }
    
    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getIsOrder() {
        return IsOrder;
    }
    
    public void setIsOrder(String isOrder) {
        IsOrder = isOrder;
    }
    
    public String getAgvRange() {
        return agvRange;
    }
    
    public void setAgvRange(String agvRange) {
        this.agvRange = agvRange;
    }
    
    public List<TaskPoint> getPoints() {
        return points;
    }
    
    public void setPoints(List<TaskPoint> points) {
        this.points = points;
    }
    
    public String getLevel() {
        return level;
    }
    
    public void setLevel(String level) {
        this.level = level;
    }
    
    public String getClearOutID() {
        return clearOutID;
    }
    
    public void setClearOutID(String clearOutID) {
        this.clearOutID = clearOutID;
    }
    
    public String getOutID() {
        return outID;
    }
    
    public void setOutID(String outID) {
        this.outID = outID;
    }
}

