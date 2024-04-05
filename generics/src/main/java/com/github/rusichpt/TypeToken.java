package com.github.rusichpt;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class TypeToken<T> {
    private Type type;

    protected TypeToken(){
        Type superClass = getClass().getGenericSuperclass();
        this.type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
    }

    public Type getType() {
        return type;
    }
}
