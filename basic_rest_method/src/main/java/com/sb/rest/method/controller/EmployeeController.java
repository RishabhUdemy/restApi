package com.sb.rest.method.controller;

import com.sb.rest.method.dto.EmployeeDTO;
import com.sb.rest.method.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO){
        return service.saveEmployeeData(employeeDTO);
    }

    @GetMapping("/{id}")
    public EmployeeDTO getEmployee(@PathVariable Long id){

        return service.getEmployeeInfo(id);
    }


    @GetMapping
    public List<EmployeeDTO> employeeDTOS(){
        return service.getAllEmployeeInfo();
    }

    @PutMapping(path = "/{employeeId}")
    public EmployeeDTO updateEmployee(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long employeeId){
       return service.updateEmployee(employeeDTO,employeeId);
    }

    @DeleteMapping(path = "/{employeeId}")
    public boolean deleteEmployee(@PathVariable Long employeeId){
        return service.deleteEmployeeById(employeeId);
    }


}
