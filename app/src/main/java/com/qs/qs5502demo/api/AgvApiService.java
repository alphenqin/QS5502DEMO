package com.qs.qs5502demo.api;

import android.content.Context;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.qs.qs5502demo.model.ApiResponse;
import com.qs.qs5502demo.model.TaskResponse;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * AGV调度系统API服务实现
 */
public class AgvApiService {
    
    private static final String TAG = "AgvApiService";
    
    private static final String BASE_URL = ApiConfig.AGV_BASE_URL;
    
    /**
     * 入库：呼叫入库
     */
    public TaskResponse callInbound(Map<String, String> params, Context context) throws IOException {
        String url = BASE_URL + "/agv/inbound/call";
        String json = HttpUtil.toJson(params);
        String response = HttpUtil.post(url, json, context);
        
        Type type = new TypeToken<ApiResponse<TaskResponse>>(){}.getType();
        ApiResponse<TaskResponse> apiResponse = HttpUtil.fromJson(response, type);
        
        if (apiResponse.isSuccess() && apiResponse.getData() != null) {
            return apiResponse.getData();
        } else {
            throw new IOException(apiResponse.getMessage());
        }
    }
    
    /**
     * 送检：呼叫送检
     */
    public TaskResponse callSendInspection(Map<String, String> params, Context context) throws IOException {
        String url = BASE_URL + "/agv/inspection/send";
        String json = HttpUtil.toJson(params);
        String response = HttpUtil.post(url, json, context);
        
        Type type = new TypeToken<ApiResponse<TaskResponse>>(){}.getType();
        ApiResponse<TaskResponse> apiResponse = HttpUtil.fromJson(response, type);
        
        if (apiResponse.isSuccess() && apiResponse.getData() != null) {
            return apiResponse.getData();
        } else {
            throw new IOException(apiResponse.getMessage());
        }
    }
    
    /**
     * 送检：空托回库
     */
    public TaskResponse returnPalletFromInspection(Map<String, String> params, Context context) throws IOException {
        String url = BASE_URL + "/agv/pallet/returnFromInspection";
        String json = HttpUtil.toJson(params);
        String response = HttpUtil.post(url, json, context);
        
        Type type = new TypeToken<ApiResponse<TaskResponse>>(){}.getType();
        ApiResponse<TaskResponse> apiResponse = HttpUtil.fromJson(response, type);
        
        if (apiResponse.isSuccess() && apiResponse.getData() != null) {
            return apiResponse.getData();
        } else {
            throw new IOException(apiResponse.getMessage());
        }
    }
    
    /**
     * 回库：呼叫托盘
     */
    public TaskResponse callPalletToInspection(Map<String, String> params, Context context) throws IOException {
        String url = BASE_URL + "/agv/pallet/callToInspection";
        String json = HttpUtil.toJson(params);
        String response = HttpUtil.post(url, json, context);
        
        Type type = new TypeToken<ApiResponse<TaskResponse>>(){}.getType();
        ApiResponse<TaskResponse> apiResponse = HttpUtil.fromJson(response, type);
        
        if (apiResponse.isSuccess() && apiResponse.getData() != null) {
            return apiResponse.getData();
        } else {
            throw new IOException(apiResponse.getMessage());
        }
    }
    
    /**
     * 回库：阀门回库
     */
    public TaskResponse returnValveToWarehouse(Map<String, String> params, Context context) throws IOException {
        String url = BASE_URL + "/agv/valve/returnToWarehouse";
        String json = HttpUtil.toJson(params);
        String response = HttpUtil.post(url, json, context);
        
        Type type = new TypeToken<ApiResponse<TaskResponse>>(){}.getType();
        ApiResponse<TaskResponse> apiResponse = HttpUtil.fromJson(response, type);
        
        if (apiResponse.isSuccess() && apiResponse.getData() != null) {
            return apiResponse.getData();
        } else {
            throw new IOException(apiResponse.getMessage());
        }
    }
    
    /**
     * 出库：呼叫出库
     */
    public TaskResponse callOutbound(Map<String, String> params, Context context) throws IOException {
        String url = BASE_URL + "/agv/outbound/call";
        String json = HttpUtil.toJson(params);
        String response = HttpUtil.post(url, json, context);
        
        Type type = new TypeToken<ApiResponse<TaskResponse>>(){}.getType();
        ApiResponse<TaskResponse> apiResponse = HttpUtil.fromJson(response, type);
        
        if (apiResponse.isSuccess() && apiResponse.getData() != null) {
            return apiResponse.getData();
        } else {
            throw new IOException(apiResponse.getMessage());
        }
    }
    
    /**
     * 出库：空托回库
     */
    public TaskResponse returnPalletFromSwap(Map<String, String> params, Context context) throws IOException {
        String url = BASE_URL + "/agv/pallet/returnFromSwap";
        String json = HttpUtil.toJson(params);
        String response = HttpUtil.post(url, json, context);
        
        Type type = new TypeToken<ApiResponse<TaskResponse>>(){}.getType();
        ApiResponse<TaskResponse> apiResponse = HttpUtil.fromJson(response, type);
        
        if (apiResponse.isSuccess() && apiResponse.getData() != null) {
            return apiResponse.getData();
        } else {
            throw new IOException(apiResponse.getMessage());
        }
    }
    
    /**
     * 取消任务
     */
    public boolean cancelTask(String outID, String operator, Context context) throws IOException {
        String url = BASE_URL + "/agv/task/cancel";
        
        Map<String, String> params = new HashMap<>();
        params.put("outID", outID);
        params.put("operator", operator);
        
        String json = HttpUtil.toJson(params);
        String response = HttpUtil.post(url, json, context);
        
        Type type = new TypeToken<ApiResponse<Map<String, Object>>>(){}.getType();
        ApiResponse<Map<String, Object>> apiResponse = HttpUtil.fromJson(response, type);
        
        if (apiResponse.isSuccess()) {
            return true;
        } else {
            throw new IOException(apiResponse.getMessage());
        }
    }
}

