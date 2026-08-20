package com.sb.rest.method.service;

import com.sb.rest.method.dto.EmployeeDTO;
import com.sb.rest.method.entity.Employee;
import com.sb.rest.method.repo.EmployeeRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepo employeeRepo, ModelMapper modelMapper) {
        this.employeeRepo = employeeRepo;
        this.modelMapper = modelMapper;
    }

    public EmployeeDTO saveEmployeeData(EmployeeDTO employeeDTO){

        Employee emp = modelMapper.map(employeeDTO,Employee.class);
        Employee saveEmployeeInfo = employeeRepo.save(emp);
        return modelMapper.map(saveEmployeeInfo,EmployeeDTO.class);

    }

    public EmployeeDTO getEmployeeInfo(Long empId){
        Employee emp =employeeRepo.findById(empId).orElse(null);
         return modelMapper.map(emp,EmployeeDTO.class);
    }

    public List<EmployeeDTO> getAllEmployeeInfo() {
        List<EmployeeDTO> emplist = employeeRepo.findAll().stream().map(employeeInfo ->
                modelMapper.map(employeeInfo, EmployeeDTO.class)
        ).toList();
        return emplist;
    }

    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, Long employeeId) {

        Optional<Employee> optionalEmployee = employeeRepo.findById(employeeId);
        Employee employee = null;
        if(optionalEmployee.isPresent()){
            employee = optionalEmployee.get();
           modelMapper.map(employeeDTO,employee);
        }else{
            employee = modelMapper.map(employeeDTO, Employee.class);
        }
        Employee saveEmployeeData = employeeRepo.save(employee);
        EmployeeDTO empDto = modelMapper.map(saveEmployeeData, EmployeeDTO.class);
        return empDto;
    }

    public boolean deleteEmployeeById(Long employeeId) {
        boolean empExist = employeeRepo.existsById(employeeId);
        if(!empExist)return false;
        employeeRepo.deleteById(employeeId);
        return true;
    }
}
