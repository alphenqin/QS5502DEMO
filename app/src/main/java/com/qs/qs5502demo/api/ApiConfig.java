package com.qs.qs5502demo.api;

/**
 * API配置类
 * 用于统一管理WMS和AGV调度系统的服务器地址
 */
public class ApiConfig {
    
    // TODO: 根据实际部署环境修改以下地址
    // WMS接口基础地址
    public static final String WMS_BASE_URL = "http://wms-server.example.com/api";
    
    // AGV调度系统接口基础地址
    public static final String AGV_BASE_URL = "http://agv-dispatch.example.com/api";
    
    /**
     * 设置WMS服务器地址（可在运行时动态配置）
     */
    public static void setWmsBaseUrl(String url) {
        // 可以通过SharedPreferences保存，这里简化处理
    }
    
    /**
     * 设置AGV服务器地址（可在运行时动态配置）
     */
    public static void setAgvBaseUrl(String url) {
        // 可以通过SharedPreferences保存，这里简化处理
    }
}

