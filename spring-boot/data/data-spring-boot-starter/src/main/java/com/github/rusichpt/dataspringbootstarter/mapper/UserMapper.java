package com.github.rusichpt.dataspringbootstarter.mapper;

import com.github.rusichpt.dataspringbootstarter.dto.UserDTO;
import com.github.rusichpt.dataspringbootstarter.entity.User;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UserMapper {
    User toUser(UserDTO userDTO);

    UserDTO toUserDTO(User user);
}
