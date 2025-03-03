package com.github.rusichpt.web.anotation;

import com.github.rusichpt.web.validator.PasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String message() default "Пароль должен содержать минимум 8 символов, хотя бы одну цифру и одну заглавную букву";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
