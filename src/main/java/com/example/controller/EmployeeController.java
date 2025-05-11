package com.example.controller;

import com.example.entity.Department;
import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Value("${dept.service.name}")
    private String deptServiceName;

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping(path = "department")
    public List<Department> getEmployeeDepartment() {
        RestTemplate restTemplate = new RestTemplate();
        System.out.println("nikhil="+deptServiceName);
        List<Department> department=restTemplate.getForObject("http://"+deptServiceName+"/department", List.class);
        return department;
    }

}
