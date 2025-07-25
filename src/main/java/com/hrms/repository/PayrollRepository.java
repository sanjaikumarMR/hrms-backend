package com.hrms.repository;

import com.hrms.model.PayrollRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PayrollRepository extends MongoRepository<PayrollRecord, String> {
    List<PayrollRecord> findByUserId(String userId);
}