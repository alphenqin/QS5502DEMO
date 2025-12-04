package com.qs.qs5502demo.model;

import com.google.gson.annotations.SerializedName;

/**
 * 登录响应
 */
public class LoginResponse extends ApiResponse<LoginResponse.LoginData> {
    
    public static class LoginData {
        @SerializedName("token")
        private String token;
        
        @SerializedName("expireAt")
        private String expireAt;
        
        @SerializedName("userName")
        private String userName;
        
        @SerializedName("roles")
        private String[] roles;
        
        public String getToken() {
            return token;
        }
        
        public void setToken(String token) {
            this.token = token;
        }
        
        public String getExpireAt() {
            return expireAt;
        }
        
        public void setExpireAt(String expireAt) {
            this.expireAt = expireAt;
        }
        
        public String getUserName() {
            return userName;
        }
        
        public void setUserName(String userName) {
            this.userName = userName;
        }
        
        public String[] getRoles() {
            return roles;
        }
        
        public void setRoles(String[] roles) {
            this.roles = roles;
        }
    }
}

