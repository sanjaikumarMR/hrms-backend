package com.hrms.repository;

import com.hrms.model.FaceDescriptor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FaceDescriptorRepository extends MongoRepository<FaceDescriptor, String> {
    FaceDescriptor findByUserId(String userId);
}
