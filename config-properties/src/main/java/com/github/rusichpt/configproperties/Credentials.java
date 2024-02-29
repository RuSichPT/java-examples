package com.github.rusichpt.configproperties;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class Credentials {
    @Length(max = 4, min = 1)
    private String authMethod;
    private String username;
    private String password;
}
