package com.hrms.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Map;

@Document("payroll")
public class PayrollRecord {
    @Id
    private String id;
    private String userId;
    private String month;
    private Map<String, Double> earnings;
    private Map<String, Double> deductions;
    private double netPay;
    private Date createdAt;

    // GETTERS
    public String getId() { return id; }

    public String getUserId() { return userId; }

    public String getMonth() { return month; }

    public Map<String, Double> getEarnings() { return earnings; }

    public Map<String, Double> getDeductions() { return deductions; }

    public double getNetPay() { return netPay; }

    public Date getCreatedAt() { return createdAt; }

    // SETTERS
    public void setId(String id) { this.id = id; }

    public void setUserId(String userId) { this.userId = userId; }

    public void setMonth(String month) { this.month = month; }

    public void setEarnings(Map<String, Double> earnings) { this.earnings = earnings; }

    public void setDeductions(Map<String, Double> deductions) { this.deductions = deductions; }

    public void setNetPay(double netPay) { this.netPay = netPay; }

    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}
