package com.github.rusichpt.custom.starter.mapper;

import com.github.rusichpt.custom.starter.dto.UserDTO;
import com.github.rusichpt.custom.starter.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class UserMapperTest {

    private final UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @Test
    void toUser() {
        UserDTO userDTO = new UserDTO("Павел", 27, "Java Developer");
        User user = mapper.toUser(userDTO);


        Assertions.assertThat(user)
                .usingRecursiveComparison()
                .ignoringFields(User.Fields.id)
                .isEqualTo(userDTO);
    }

    @Test
    void toUserDTO() {
        User user = new User(null,"Павел", 27, "Java Developer");
        UserDTO userDTO = mapper.toUserDTO(user);

        Assertions.assertThat(userDTO)
                .usingRecursiveComparison()
                .isEqualTo(user);
    }
}