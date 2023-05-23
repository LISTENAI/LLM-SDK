package com.listenai.llm.models.response;

import com.alibaba.fastjson2.annotation.JSONField;

public class SparkError {
    public SparkError() {}

    public SparkError(int statusCode, String code, String message, String details) {
        this.statusCode = statusCode;
        this.code = code;
        this.message = message;
        this.details = details;
    }

    @JSONField(name = "statusCode")
    private int statusCode;
    
    @JSONField(name = "code")
    private String code;
    
    @JSONField(name = "error")
    private String message;
    
    @JSONField(name = "message")
    private String details;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
