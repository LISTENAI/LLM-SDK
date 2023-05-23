package com.listenai.llm.models.request;

import com.alibaba.fastjson2.annotation.JSONField;

public class SparkMessage {
    public SparkMessage() {}

    public SparkMessage(String role, String content) {
        this.role = role;
        this.content = content;
    }

    @JSONField(name = "role")
    private String role;

    @JSONField(name = "content")
    private String content;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
