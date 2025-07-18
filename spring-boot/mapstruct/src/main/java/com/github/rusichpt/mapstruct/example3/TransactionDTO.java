package com.github.rusichpt.mapstruct.example3;

import lombok.Data;

@Data
public class TransactionDTO {
    private String uuid;
    private Long totalInCents;
}
