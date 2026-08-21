package com.sb.rest.method.entity;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="employees")
@Getter
@Setter
@EqualsAndHashCode
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long empId;
    private String name;
    private String address;
    private String email;
    private Integer age;
    private String role;
    private Double salary;
    private String designation;
    private LocalDate dateOfJoining;
    private Boolean isActive;
}
