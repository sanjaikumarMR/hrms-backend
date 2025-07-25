package com.hrms.service;

import com.hrms.model.PayrollRecord;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Map;
import java.util.stream.Stream;


@Service
public class PDFGeneratorService {

    public ByteArrayInputStream generatePayslipPDF(PayrollRecord record) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph("Employee Payslip", headerFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph("Employee ID: " + record.getUserId()));
            document.add(new Paragraph("Month: " + record.getMonth()));
            document.add(new Paragraph("Generated on: " + record.getCreatedAt()));
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{3, 2, 3, 2});

            // Header Row
            Stream.of("Earnings", "Amount", "Deductions", "Amount")
                    .forEach(headerTitle -> {
                        PdfPCell header = new PdfPCell();
                        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        header.setPhrase(new Phrase(headerTitle));
                        table.addCell(header);
                    });

            int rows = Math.max(record.getEarnings().size(), record.getDeductions().size());
            Object[] earningsKeys = record.getEarnings().keySet().toArray();
            Object[] deductionKeys = record.getDeductions().keySet().toArray();

            for (int i = 0; i < rows; i++) {
                // Earnings
                if (i < earningsKeys.length) {
                    String key = (String) earningsKeys[i];
                    table.addCell(key);
                    table.addCell(String.format("%.2f", record.getEarnings().get(key)));
                } else {
                    table.addCell("");
                    table.addCell("");
                }

                // Deductions
                if (i < deductionKeys.length) {
                    String key = (String) deductionKeys[i];
                    table.addCell(key);
                    table.addCell(String.format("%.2f", record.getDeductions().get(key)));
                } else {
                    table.addCell("");
                    table.addCell("");
                }
            }

            // Totals
            double grossTotal = record.getEarnings().values().stream().mapToDouble(Double::doubleValue).sum();
            double deductionsTotal = record.getDeductions().values().stream().mapToDouble(Double::doubleValue).sum();
            double netPay = record.getNetPay();

            table.addCell("Gross Total");
            table.addCell(String.format("%.2f", grossTotal));
            table.addCell("Deductions Total");
            table.addCell(String.format("%.2f", deductionsTotal));

            PdfPCell netCell = new PdfPCell(new Phrase("Net Pay"));
            netCell.setColspan(3);
            netCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(netCell);
            table.addCell(String.format("%.2f", netPay));

            document.add(table);
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
