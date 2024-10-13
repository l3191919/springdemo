package com.lyz.annotation;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class NotNullNumberValidator implements ConstraintValidator<NotNullNumber, Number> {

    @Override
    public boolean isValid(Number value, ConstraintValidatorContext context) {
        // 如果value为null，则验证不通过
        return value != null;
    }
}