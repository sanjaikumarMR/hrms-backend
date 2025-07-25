package com.hrms.controller;

import com.hrms.model.PayrollRequest;
import com.hrms.model.PayrollRecord;
import com.hrms.repository.PayrollRepository;
import com.hrms.service.PDFGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/payroll")
@CrossOrigin(origins = "*")
public class PayrollController {

    @Autowired
    private PayrollRepository payrollRepository;

    @Autowired
    private PDFGeneratorService pdfGeneratorService;

    @PostMapping("/save")
    public ResponseEntity<String> savePayroll(@RequestBody PayrollRequest request) {
      PayrollRecord payroll = new PayrollRecord();
       payroll.setUserId(request.getUserId());
payroll.setMonth(request.getMonth());
payroll.setEarnings(request.getEarnings());
payroll.setDeductions(request.getDeductions());
payroll.setNetPay(request.getNetPay());
payroll.setCreatedAt(new Date());

payrollRepository.save(payroll);



        return ResponseEntity.ok("Payroll saved successfully.");
    }

    @GetMapping("/payslip/{userId}")
    public ResponseEntity<InputStreamResource> generatePayslip(@PathVariable String userId) {
        List<PayrollRecord> records = payrollRepository.findByUserId(userId);
        if (records.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        PayrollRecord latestRecord = records.get(records.size() - 1);
        ByteArrayInputStream pdfStream = pdfGeneratorService.generatePayslipPDF(latestRecord);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=payslip_" + userId + ".pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdfStream));
    }
}
