// File: src/main/java/com/hrms/model/PayrollRequest.java
package com.hrms.model;

import java.util.Map;

public class PayrollRequest {
    private String userId;
    private String month;
    private Map<String, Double> earnings;
    private Map<String, Double> deductions;
    private double netPay;

    // Getter methods
    public String getUserId() {
        return userId;
    }

    public String getMonth() {
        return month;
    }

    public Map<String, Double> getEarnings() {
        return earnings;
    }

    public Map<String, Double> getDeductions() {
        return deductions;
    }

    public double getNetPay() {
        return netPay;
    }

    // Optional: Setter methods if needed
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setEarnings(Map<String, Double> earnings) {
        this.earnings = earnings;
    }

    public void setDeductions(Map<String, Double> deductions) {
        this.deductions = deductions;
    }

    public void setNetPay(double netPay) {
        this.netPay = netPay;
    }
}
