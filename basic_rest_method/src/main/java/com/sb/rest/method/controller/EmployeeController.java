package com.sb.rest.method.controller;

import com.sb.rest.method.dto.EmployeeDTO;
import com.sb.rest.method.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody @Valid EmployeeDTO employeeDTO){
        EmployeeDTO saveEmployeeInfo = service.saveEmployeeData(employeeDTO);
        return new ResponseEntity<>(saveEmployeeInfo, HttpStatus.CREATED);

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
    public ResponseEntity<List<EmployeeDTO>> employeeDTOS(){
        return ResponseEntity.ok(service.getAllEmployeeInfo());

    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long employeeId){
       EmployeeDTO employeeInfoDto = service.updateEmployee(employeeDTO,employeeId);
       return ResponseEntity.ok(employeeInfoDto);

    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable Long employeeId){
        Boolean isEmployeeAvailable = service.deleteEmployeeById(employeeId);
        if(!isEmployeeAvailable) ResponseEntity.notFound().build();
        return ResponseEntity.ok(true);
    }

    @PatchMapping(path="/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeData(@RequestBody Map<String,Object> mapKeyValue,@PathVariable Long employeeId){
        EmployeeDTO employeeDTO = service.updatePartialEmployeeData(mapKeyValue,employeeId);
        if(employeeDTO == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }


}
