package com.github.rusichpt.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdvancedMockitoTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testMockitoFeatures() {
        // 1. Stubbing с разными возвращаемыми значениями
        when(userRepository.findById(1L))
                .thenReturn(Optional.of(new User(1L, "Павел", 27, "Java developer")))
                .thenReturn(Optional.empty());

        User first = userService.findUser(1L);
        User second = userService.findUser(1L);

        assertEquals("Павел", first.getName());
        assertNull(second);

        // 2. Argument matchers
        when(userRepository.findByName(anyString()))
                .thenReturn(Optional.of(new User(2L, "Александр", 20, "C++ developer")));

        User found = userService.findUserByName("any name");
        assertEquals("Александр", found.getName());

        // 3. Проверка количества вызовов
        verify(userRepository, times(2)).findById(1L);
        verify(userRepository, never()).delete(any());
        verify(userRepository, atLeast(1)).findByName(anyString());

        // 4. Проверка порядка вызовов
        InOrder inOrder = inOrder(userRepository); // Создаём объект для проверки порядка вызовов на userRepository
        inOrder.verify(userRepository, times(2)).findById(1L);
        inOrder.verify(userRepository).findByName(anyString());

        // 5. Stubbing для void методов
        doNothing().when(userRepository).delete(any());
        //doThrow(new RuntimeException("Database error"))
        //        .when(userRepository).save(any());

        userService.deleteUser(1L);


        // 6. Проверка аргументов
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class); // @Captor
        userService.saveUser(new User(3L, "Дмитрий", 18, "С# developer")); // Вызываем метод сервиса, который внутри должен вызвать userRepository.save(...)
        verify(userRepository).save(userCaptor.capture()); // Проверяем, что save() был вызван, и захватываем переданный аргумент

        User capturedUser = userCaptor.getValue(); //После захвата — проверяем поля объекта:
        assertEquals("Дмитрий", capturedUser.getName());

        // 7. Ответы на основе аргументов
        when(userRepository.findByName(anyString()))
                .thenAnswer(invocation -> {
                    // invocation — объект, содержащий информацию о вызове метода

                    // Получаем 1-й аргумент (индекс 0), который был передан в findByName()
                    String name = invocation.getArgument(0);
                    // Динамически формируем ответ на основе этого аргумента
                    return Optional.of(new User(4L, name.toUpperCase(), 25, "Python developer"));
                });

        User dynamicUser = userService.findUserByName("alice");
        assertEquals("ALICE", dynamicUser.getName());
    }
}
