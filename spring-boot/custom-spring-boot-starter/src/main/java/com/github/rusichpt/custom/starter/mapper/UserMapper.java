package com.github.rusichpt.custom.starter.mapper;

import com.github.rusichpt.custom.starter.dto.UserDTO;
import com.github.rusichpt.custom.starter.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User toUser(UserDTO userDTO);

    UserDTO toUserDTO(User user);
}
