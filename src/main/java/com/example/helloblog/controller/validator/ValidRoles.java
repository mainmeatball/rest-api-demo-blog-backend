package com.example.helloblog.controller.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RolesValidator.class)
@Documented
public @interface ValidRoles {
    String message() default "Invalid roles";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
