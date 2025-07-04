package com.github.rusichpt.jasper.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity {
    private String name;
    private String state;
    private String executorName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String priority;
}
