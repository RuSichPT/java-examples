package com.github.rusichpt.jasper.controller;

import com.github.rusichpt.jasper.dto.ReportRequest;
import com.github.rusichpt.jasper.dto.ReportResponse;
import com.github.rusichpt.jasper.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportController {
    private final ReportService reportService;

    @PostMapping
    @Operation(summary = "Сгенерировать отчет")
    public ReportResponse generateReport(@RequestBody ReportRequest request) {
        reportService.generateReport(request);
        return new ReportResponse(true);
    }
}
