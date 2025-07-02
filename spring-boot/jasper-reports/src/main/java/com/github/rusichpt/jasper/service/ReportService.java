package com.github.rusichpt.jasper.service;

import com.github.rusichpt.jasper.dto.ReportRequest;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ResourceLoader resourceLoader;

    public void generateReport(ReportRequest request) {
        try {
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("processId", Long.valueOf(request.getProcessId()));

            Resource resource = resourceLoader.getResource("classpath:template/report_template.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(resource.getInputStream());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

            JasperExportManager.exportReportToPdfFile(jasperPrint, "report.pdf");
        } catch (JRException | IOException e) {
            throw new RuntimeException(e);
        }

    }
}
