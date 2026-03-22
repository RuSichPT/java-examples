package com.github.rusichpt.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MainTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetUser() {
        // given
        User expectedUser = new User(1L, "Павел", 27, "Java developer");

        when(userRepository.findById(1L)).thenReturn(Optional.of(expectedUser));

        // when
        User actualUser = userService.findUser(1L);

        // then
        assertEquals("Павел", actualUser.getName());
        verify(userRepository).findById(1L);
    }
}