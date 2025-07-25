package com.hrms.controller;

import com.hrms.model.Attendance;
import com.hrms.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "https://sanjaikumarmr.github.io")
public class AttendanceController {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @PostMapping
    public Attendance markAttendance(@RequestBody Attendance attendance) {
        attendance.setTimestamp(LocalDateTime.now().toString());
        attendance.setStatus("Present");
        return attendanceRepository.save(attendance);
    }
}
