package com.listenai.llm.models.response;

import com.alibaba.fastjson2.annotation.JSONField;

import java.util.ArrayList;

public class CompletionResponse {
    @JSONField(name = "choices")
    private ArrayList<CompletionChoice> choices;

    @JSONField(name = "created")
    private int createdAt;

    @JSONField(name = "id")
    private String id;

    @JSONField(name = "object")
    private String object;

    @JSONField(name = "usage")
    private SparkUsage usage;

    public ArrayList<CompletionChoice> getChoices() {
        return choices;
    }

    public void setChoices(ArrayList<CompletionChoice> choices) {
        this.choices = choices;
    }

    public int getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(int createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public SparkUsage getUsage() {
        return usage;
    }

    public void setUsage(SparkUsage usage) {
        this.usage = usage;
    }
}
