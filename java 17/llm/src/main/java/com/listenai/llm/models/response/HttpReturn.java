package com.listenai.llm.models.response;

public class HttpReturn {
    public HttpReturn() {}

    public HttpReturn(int code, String content) {
        this.code = code;
        this.content = content;
    }

    private int code;

    private String content;

    public int getCode() {
        return code;
    }

    public String getContent() {
        return content;
    }
}
