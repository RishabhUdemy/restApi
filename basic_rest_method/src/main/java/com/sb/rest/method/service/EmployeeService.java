package com.sb.rest.method.service;

import com.sb.rest.method.dto.EmployeeDTO;
import com.sb.rest.method.entity.Employee;
import com.sb.rest.method.repo.EmployeeRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
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

    public Optional<EmployeeDTO> getEmployeeInfo(Long empId){
//        Employee emp =employeeRepo.findById(empId).orElse(null);
//         return modelMapper.map(emp,EmployeeDTO.class);

        return employeeRepo.findById(empId).map(employeeData->modelMapper.map(employeeData,EmployeeDTO.class));
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

    public Boolean deleteEmployeeById(Long employeeId) {
        boolean empExist = employeeExistById(employeeId);;
        if(!empExist)return false;
        employeeRepo.deleteById(employeeId);
        return true;
    }

    public EmployeeDTO updatePartialEmployeeData(Map<String, Object> mapKeyValue, Long employeeId) {
        boolean empExist = employeeExistById(employeeId);
        if(!empExist) return null;
        Employee employeeEntity = employeeRepo.findById(employeeId).get();
        mapKeyValue.forEach((field,value)->{
            Field fieldUpdate = ReflectionUtils.findField(Employee.class,field);

            if(fieldUpdate == null){
                throw new IllegalArgumentException("Invalid Field"+field);
            }
            ReflectionUtils.makeAccessible(fieldUpdate);

            Object convertValue = convertValue(fieldUpdate,value);
            ReflectionUtils.setField(fieldUpdate,employeeEntity,convertValue);
//            fieldUpdate.setAccessible(true);
//            ReflectionUtils.setField(fieldUpdate,employeeEntity,value);
        });
        return modelMapper.map(employeeRepo.save(employeeEntity),EmployeeDTO.class);
    }

    private boolean employeeExistById(Long employeeId){
        boolean empExist = employeeRepo.existsById(employeeId);
        return empExist;
    }

    private Object convertValue(Field field, Object value) {

        Class<?> fieldType = field.getType();

        if (value == null) {
            return null;
        }

        if (fieldType.equals(LocalDate.class)) {
            return LocalDate.parse(value.toString());
        }

        if (fieldType.equals(Long.class)) {
            return Long.valueOf(value.toString());
        }

        if (fieldType.equals(Integer.class)) {
            return Integer.valueOf(value.toString());
        }

        if (fieldType.equals(Boolean.class)) {
            return Boolean.valueOf(value.toString());
        }

        if (fieldType.equals(String.class)) {
            return value.toString();
        }

        return value;
    }
}
