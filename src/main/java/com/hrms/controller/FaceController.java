package com.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hrms.service.FaceService;
import com.hrms.model.FaceRegisterRequest;

import java.util.List;

@CrossOrigin(origins = "https://sanjaikumarmr.github.io") // Enable CORS for your frontend
@RestController
public class FaceController {

    @Autowired
    private FaceService faceService;

    // POST: Register face
    @PostMapping("/api/face/register")
    public ResponseEntity<String> registerFace(@RequestBody FaceRegisterRequest request) {
        faceService.saveDescriptor(request.getUserId(), request.getDescriptor());
        return ResponseEntity.ok("Face registered");
    }

    // GET: Fetch face descriptor for verification
    @GetMapping("/api/face/{userId}")
    public ResponseEntity<?> getFaceDescriptor(@PathVariable String userId) {
        List<Float> descriptor = faceService.getDescriptor(userId);
        if (descriptor == null || descriptor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(descriptor);
    }
}
