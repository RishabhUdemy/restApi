package com.sb.rest.method.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class EmployeeRoleValidatorLogic implements ConstraintValidator<EmployeeRoleValidator,String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null) return false;
        List<String> listOfRole = List.of("ADMIN","USER");
        return listOfRole.contains(value);
    }
}
