package com.github.rusichpt.data.jdbc.db2;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@Table("bic_list")
public class BicListEntity {
    @Id
    private Long id;
    private String bic;
    private String bankName;
    private LocalDate dateIn;
    private LocalDate dateOut;
}
