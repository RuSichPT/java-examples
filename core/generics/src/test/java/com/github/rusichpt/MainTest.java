package com.github.rusichpt;

import org.junit.jupiter.api.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    @Test
    void givenContainerClassWithGenericType_whenTypeParameterUsed_thenReturnsClassType() {
        var stringContainer = new ContainerTypeFromTypeParameter<>(String.class);
        Class<String> containerClass = stringContainer.getClazz();

        assertEquals(String.class, containerClass);
    }

    @Test
    void givenContainerClassWithGenericType_whenReflectionUsed_thenReturnsClassType() {
        var stringContainer = new ContainerTypeFromReflection<>("Hello Java");
        Class<?> stringClazz = stringContainer.getClazz();
        assertEquals(String.class, stringClazz);

        var integerContainer = new ContainerTypeFromReflection<>(1);
        Class<?> integerClazz = integerContainer.getClazz();
        assertEquals(Integer.class, integerClazz);
    }

    @Test
    void giveContainerClassWithGenericType_whenTypeTokenUsed_thenReturnsClassType() {
        class ContainerTypeFromTypeToken extends TypeToken<List<String>> {
        }

        var container = new ContainerTypeFromTypeToken();
        ParameterizedType type = (ParameterizedType) container.getType();
        Type actualTypeArgument = type.getActualTypeArguments()[0];

        assertEquals(String.class, actualTypeArgument);
    }

    @Test
    void giveContainerClassWithGenericType_whenMyTypeTokenUsed_thenReturnsClassType() {
        class ContainerTypeFromTypeToken implements MyTypeToken<String> {
            @Override
            public Class<String> getTypeToken() {
                return String.class;
            }
        }

        var container = new ContainerTypeFromTypeToken();
        Class<String> actualTypeArgument = container.getTypeToken();

        assertEquals(String.class, actualTypeArgument);
    }
}