package com.lyz.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 在类中的属性上使用使用
 * @NotEmptyOrNull
 *如:
 * @NotEmptyOrNull
 *     private String name;
 * 以及在接收端使用
 * @Valid
 * 如:
 * @PostMapping("/user")
 *     public String createUser(@Valid @RequestBody User user)
 */

public class NotEmptyOrNullValidator implements ConstraintValidator<NotEmptyOrNull,String> {
    @Override
    public void initialize(NotEmptyOrNull constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Boolean flag =  value!=null && !value.isEmpty();
//        // 验证逻辑
//        if (!flag) {
//
//            // 使用 context 构建并添加自定义的验证失败信息
//            // 这里不直接操作 basePath，而是通过构建 ConstraintViolation 对象来定制
//            String defaultMessage = context.getDefaultConstraintMessageTemplate();
//            // 可以动态修改消息模板或属性路径
//            String propertyPath = "your.custom.path.here"; // 自定义路径
//            context.disableDefaultConstraintViolation()
//                    .buildConstraintViolationWithTemplate(defaultMessage)
//                    .addPropertyNode(propertyPath) // 这里可以视为设置属性路径
//                    .addConstraintViolation();
//
//            return false;
//        }
//        return true;


        return value!=null && !value.isEmpty();
    }


}
