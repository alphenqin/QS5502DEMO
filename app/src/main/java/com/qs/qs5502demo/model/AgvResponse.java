package com.qs.qs5502demo.model;

import com.google.gson.annotations.SerializedName;

/**
 * AGV调度系统统一响应格式
 * code为字符串类型：20000成功，90000失败
 */
public class AgvResponse {
    @SerializedName("code")
    private String code;
    
    @SerializedName("message")
    private String message;
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public boolean isSuccess() {
        return "20000".equals(code);
    }
}

