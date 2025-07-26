package com.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hrms.service.FaceService;
import com.hrms.model.FaceRegisterRequest;

import java.util.List;


public class HealthController {

    @GetMapping("/ping")
    public String ping() {
        return "OK";
    }
}
