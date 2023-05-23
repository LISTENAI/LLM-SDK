package com.listenai.llm.models.response;

import com.alibaba.fastjson2.annotation.JSONField;
import com.listenai.llm.models.request.SparkMessage;

public class CompletionChoice {
    @JSONField(name = "finish_reason")
    private String finishReason;

    @JSONField(name = "index")
    private int index;

    @JSONField(name = "message")
    private SparkMessage message;

    public String getFinishReason() {
        return finishReason;
    }

    public void setFinishReason(String finishReason) {
        this.finishReason = finishReason;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public SparkMessage getMessage() {
        return message;
    }

    public void setMessage(SparkMessage message) {
        this.message = message;
    }
}
