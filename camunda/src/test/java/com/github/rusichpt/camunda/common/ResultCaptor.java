package com.github.rusichpt.camunda.common;

import lombok.Getter;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

@Getter
public class ResultCaptor<T> implements Answer<T> {
    private T result = null;
    private boolean isDone = false;

    @SuppressWarnings("unchecked")
    @Override
    public T answer(InvocationOnMock invocationOnMock) throws Throwable {
        result = (T) invocationOnMock.callRealMethod();
        isDone = true;
        return result;
    }
}