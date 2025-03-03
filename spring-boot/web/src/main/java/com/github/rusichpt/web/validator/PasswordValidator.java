package com.github.rusichpt.web.validator;

import com.github.rusichpt.web.anotation.ValidPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        if (password == null) {
            return false;
        }
        return password.length() >= 8 &&
                password.matches(".*\\d.*") &&  // хотя бы одна цифра
                password.matches(".*[A-Z].*"); // хотя бы одна заглавная буква
    }
}
