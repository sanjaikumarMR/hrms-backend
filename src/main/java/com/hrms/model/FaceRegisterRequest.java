package com.hrms.model;

import java.util.List;
    
public class FaceRegisterRequest {
    private String userId;
    private List<Float> descriptor;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Float> getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(List<Float> descriptor) {
        this.descriptor = descriptor;
    }
}
