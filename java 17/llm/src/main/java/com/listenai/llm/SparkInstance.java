package com.listenai.llm;

import com.alibaba.fastjson2.JSON;
import com.listenai.llm.models.request.CompletionOptions;
import com.listenai.llm.models.request.SparkOptions;
import com.listenai.llm.models.response.CompletionResponse;
import com.listenai.llm.models.response.SparkError;

public class SparkInstance {
    private SparkOptions _options;

    public SparkInstance(SparkOptions options) {
        _options = options;
    }

    public CompletionResponse Conversations(CompletionOptions options) {
        var completionOptJson = JSON.toJSONString(options);
        var resp = Helpers.HttpOperation(Constants.UrlFactory.Completions, "POST", _options.getToken(),
                completionOptJson, "application/json");
        try {
            if (resp.getCode() != 200) {
                var err = JSON.parseObject(resp.getContent(), SparkError.class);
                throw new SparkException(err);
            } else {
                var result = JSON.parseObject(resp.getContent(), CompletionResponse.class);
                return result;
            }
        } catch (Exception e) {
            if (e instanceof SparkException) {
                throw e;
            } else {
                var err = new SparkError(402, "FST_ERR_OTHER", e.getMessage(), "SDK Error");
                throw new SparkException(err);
            }
        }
    }
}
