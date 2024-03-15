package com.gitgub.rusichpt.dataspringbootstarter.mapper;

import com.gitgub.rusichpt.dataspringbootstarter.dto.UserDTO;
import com.gitgub.rusichpt.dataspringbootstarter.entity.User;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UserMapper {
    User toUser(UserDTO userDTO);

    UserDTO toUserDTO(User user);
}
