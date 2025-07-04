package com.github.rusichpt.jasper.controller;

import com.github.rusichpt.jasper.dto.ReportRequest;
import com.github.rusichpt.jasper.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    @ApiResponse(
            responseCode = "200",
            description = "PDF документ",
            content = @Content(
                    mediaType = "application/pdf",
                    schema = @Schema(type = "string", format = "binary")
            )
    )
    public ResponseEntity<byte[]> generateReport(@RequestBody ReportRequest request) {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(reportService.generateReport(request));
    }
}
