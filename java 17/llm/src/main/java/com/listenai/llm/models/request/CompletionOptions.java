package com.listenai.llm.models.request;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.annotation.JSONField;

import java.util.ArrayList;

public class CompletionOptions {
    @JSONField(name = "messages")
    private ArrayList<SparkMessage> messages;

    @JSONField(name = "uid")
    private String userId;

    @JSONField(name = "auditing")
    private String auditing;

    @JSONField(name = "domain")
    private String domain;

    @JSONField(name = "random_threshold")
    private float randomThreshold;

    @JSONField(name = "max_tokens")
    private Integer maxTokens;

    @JSONField(name = "stream")
    private Boolean stream;

    public CompletionOptions() {
        messages = new ArrayList<>();
        maxTokens = 1024;
    }

    public void addMessage(String message, String role) {
        SparkMessage msg = new SparkMessage(role, message);
        this.messages.add(msg);
    }

    public void addMessage(SparkMessage msg) {
        this.messages.add(msg);
    }

    public ArrayList<SparkMessage> getMessages() {
        return messages;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAuditing() {
        return auditing;
    }

    public void setAuditing(String auditing) {
        if (auditing != "default") {
            throw new RuntimeException("Valid options for [auditing] are [default]");
        }
        this.auditing = auditing;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        if (domain != "general") {
            throw new RuntimeException("Valid options for [domain] are [general]");
        }
        this.domain = domain;
    }

    public float getRandomThreshold() {
        return randomThreshold;
    }

    public void setRandomThreshold(float randomThreshold) {
        if (randomThreshold < 0.0 || randomThreshold > 1.0) {
            throw new RuntimeException("Valid options for [random_threshold] are [0-1]");
        }
        this.randomThreshold = randomThreshold;
    }

    public Integer getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(Integer maxTokens) {
        if (maxTokens < 1 || maxTokens > 4096) {
            throw new RuntimeException("Valid options for [max_tokens] are [1-4096]");
        }
        this.maxTokens = maxTokens;
    }

    public Boolean getStream() {
        return stream;
    }

    public void setStream(Boolean stream) {
        if (stream) {
            throw new RuntimeException("Valid options for [stream] are [false]");
        }
        this.stream = false;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
