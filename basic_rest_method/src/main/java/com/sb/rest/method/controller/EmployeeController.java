package com.sb.rest.method.controller;

import com.sb.rest.method.dto.EmployeeDTO;
import com.sb.rest.method.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable Long id){
        Optional<EmployeeDTO > employeeDTO = service.getEmployeeInfo(id);
//        return new ResponseEntity<>().ok().body(employeeDTO);
        return employeeDTO
                .map(emp->ResponseEntity.ok(emp))
                .orElse(ResponseEntity.notFound().build());
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

    @PatchMapping(path="/{employeeId}")
    public EmployeeDTO updatePartialEmployeeData(@RequestBody Map<String,Object> mapKeyValue,@PathVariable Long employeeId){
        return service.updatePartialEmployeeData(mapKeyValue,employeeId);
    }


}
