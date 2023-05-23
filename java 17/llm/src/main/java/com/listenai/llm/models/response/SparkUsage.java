package com.listenai.llm.models.response;

import com.alibaba.fastjson2.annotation.JSONField;

public class SparkUsage {
    @JSONField(name = "completion_tokens")
    private int completionTokens;

    @JSONField(name = "prompt_tokens")
    private int promptTokens;

    @JSONField(name = "question_tokens")
    private int questionTokens;

    @JSONField(name = "total_tokens")
    private int totalTokens;

    public int getCompletionTokens() {
        return completionTokens;
    }

    public void setCompletionTokens(int completionTokens) {
        this.completionTokens = completionTokens;
    }

    public int getPromptTokens() {
        return promptTokens;
    }

    public void setPromptTokens(int promptTokens) {
        this.promptTokens = promptTokens;
    }

    public int getQuestionTokens() {
        return questionTokens;
    }

    public void setQuestionTokens(int questionTokens) {
        this.questionTokens = questionTokens;
    }

    public int getTotalTokens() {
        return totalTokens;
    }

    public void setTotalTokens(int totalTokens) {
        this.totalTokens = totalTokens;
    }
}
