package com.hrms.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "face_data")
public class FaceDescriptor {

    @Id
    private String userId; // _id field in MongoDB

    private List<Float> descriptor;

    // Getters and Setters
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
