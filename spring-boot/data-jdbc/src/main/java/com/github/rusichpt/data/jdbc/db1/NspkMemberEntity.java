package com.github.rusichpt.data.jdbc.db1;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("nspk_members")
public class NspkMemberEntity {
    @Id
    private Long id;
    private String nspkId;
    private String bic;
    private String bankName;
}

