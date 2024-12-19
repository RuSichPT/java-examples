package com.github.rusichpt.custom.starter.mapper;

import com.github.rusichpt.custom.starter.dto.UserDTO;
import com.github.rusichpt.custom.starter.model.User;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UserMapper {
    User toUser(UserDTO userDTO);

    UserDTO toUserDTO(User user);
}
