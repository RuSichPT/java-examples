package com.github.rusichpt.jasper.service;

import com.github.rusichpt.jasper.entity.TaskEntity;
import com.github.rusichpt.jasper.repo.TaskRepository;
import com.github.rusichpt.jasper.repo.ProcessRepository;
import com.github.rusichpt.jasper.dto.ReportRequest;
import com.github.rusichpt.jasper.entity.ProcessEntity;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ResourceLoader resourceLoader;
    private final ProcessRepository processRepository;
    private final TaskRepository taskRepository;

    public byte[] generateReport(ReportRequest request) {
        ProcessEntity processEntity = processRepository.findById(request.getProcessId())
                .orElseThrow();
        List<TaskEntity> taskEntities = taskRepository.findAll();

        try {
            Resource resource = resourceLoader.getResource("classpath:template/report_template.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(resource.getInputStream());

            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("processId", request.getProcessId());
            JRBeanCollectionDataSource listDataSource = new JRBeanCollectionDataSource(List.of(processEntity));
            JRBeanCollectionDataSource tableDataSource = new JRBeanCollectionDataSource(taskEntities);
            parameters.put("listDataSource", listDataSource);
            parameters.put("tableDataSource", tableDataSource);

            JRBeanCollectionDataSource mainDataSource = new JRBeanCollectionDataSource(List.of(processEntity));

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, mainDataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException | IOException e) {
            throw new RuntimeException(e);
        }

    }
}
