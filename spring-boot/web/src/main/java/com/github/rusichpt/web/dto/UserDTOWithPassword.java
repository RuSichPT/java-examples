package com.github.rusichpt.web.dto;

import com.github.rusichpt.custom.starter.dto.UserDTO;
import com.github.rusichpt.web.anotation.ValidPassword;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDTOWithPassword {
    private UserDTO userDTO;
    @NotNull
    @ValidPassword
    private String password;
}
