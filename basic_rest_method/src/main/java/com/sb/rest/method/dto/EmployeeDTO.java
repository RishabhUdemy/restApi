package com.sb.rest.method.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sb.rest.method.annotation.EmployeeRoleValidator;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
public class EmployeeDTO {

    private Long empId;

    @NotBlank(message="Name should not be blank")
    private String name;
    private String address;

    @NotBlank(message="Employee Designation should not be blank")
    private String designation;

    @NotBlank(message="Email of the employee should not be blank")
    @Email(message="Email should be valid email")
    private String email;

    @NotNull(message="Age of the employee should not be blank")
    @Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 80, message="Age should not be more than 80")
    private Integer age;

    @NotNull(message="Salary should not be null")
    @Positive(message="Salary Should be positive Number")
    @Digits(integer = 6,fraction = 2, message="The salary should be in the form of XXXXXX.YY")
    private Double salary;

    @NotBlank(message="Role cannot be Blank")
//    @Pattern(regexp = "^(ADMIN|USER)$",message="Employee Role should be ADMIN or USER")
    @EmployeeRoleValidator
    private String role;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "Date of joining should be Present day or past day")
    private LocalDate dateOfJoining;
    private Boolean isActive;
}
