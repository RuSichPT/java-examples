package com.github.rusichpt;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @NonNull
    @MyAnnotation2
    private Double balance;

    @NonNull
    @MyAnnotation1
    private String accountHolder;
}
