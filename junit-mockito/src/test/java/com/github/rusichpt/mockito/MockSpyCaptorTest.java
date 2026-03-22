package com.github.rusichpt.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MockSpyCaptorTest {

    @Mock
    private List<String> mockList;  // Полностью мокированный объект

    @Spy
    private List<String> spyList = new ArrayList<>();  // Реальный объект с возможностью переопределения

    @Captor
    private ArgumentCaptor<String> captor;

    @Test
    void testMock() {
        // Mock - все методы по умолчанию ничего не делают
        when(mockList.size()).thenReturn(10);

        assertEquals(10, mockList.size());
        verify(mockList).size();
    }

    @Test
    void testSpy() {
        // Spy - вызывает реальные методы, если не застаблены
        spyList.add("one");
        spyList.add("two");

        assertEquals(2, spyList.size());
        assertEquals("one", spyList.get(0));

        // Переопределяем метод
        when(spyList.size()).thenReturn(100);
        assertEquals(100, spyList.size());  // Уже не 2!

        // Проверка, что реальные методы вызывались
        verify(spyList).add("one");
        verify(spyList).add("two");
    }

    @Test
    void testCaptor() {
        // Использование ArgumentCaptor
        mockList.add("test1");
        mockList.add("test2");

        verify(mockList, times(2)).add(captor.capture());

        List<String> capturedValues = captor.getAllValues();
        assertEquals(Arrays.asList("test1", "test2"), capturedValues);
    }
}
