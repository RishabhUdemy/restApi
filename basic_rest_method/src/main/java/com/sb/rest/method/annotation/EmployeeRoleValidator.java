package com.sb.rest.method.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

//@Constraint(validatedBy = { })
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = {EmployeeRoleValidatorLogic.class})
public @interface EmployeeRoleValidator {

    String message() default "Role of the Employee Should be ADMIN or USER";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
