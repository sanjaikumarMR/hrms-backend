package com.hrms.service;

import com.hrms.model.FaceDescriptor;
import com.hrms.repository.FaceDescriptorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaceService {

    @Autowired
    private FaceDescriptorRepository faceDescriptorRepository;

    public void saveDescriptor(String userId, List<Float> descriptor) {
        FaceDescriptor faceDescriptor = new FaceDescriptor();
        faceDescriptor.setUserId(userId);
        faceDescriptor.setDescriptor(descriptor);

        faceDescriptorRepository.save(faceDescriptor); // Will update if userId exists
    }

    public List<Float> getDescriptor(String userId) {
        FaceDescriptor faceDescriptor = faceDescriptorRepository.findByUserId(userId);
        return faceDescriptor != null ? faceDescriptor.getDescriptor() : null;
    }
}
