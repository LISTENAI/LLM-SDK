package com.listenai.llm;

import com.listenai.llm.models.response.SparkError;

public class SparkException extends RuntimeException {
    public SparkException(SparkError error) {
        super(error.getMessage());
        this.setError(error);
    }

    private SparkError error;

    public SparkError getError() {
        return error;
    }

    public void setError(SparkError error) {
        this.error = error;
    }
}
