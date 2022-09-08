package com.example.springbootapp.controller;

import com.example.springbootapp.model.Employee;
import com.example.springbootapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private EmployeeService service;

    @Autowired
    public EmployeeController(final EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployeeDetails() {
        return service.getEmployeeDetails();
    }

    @GetMapping("/empById/{empId}")
    public List<Employee> getEmployeeById(@PathVariable final String empId) {
        return service.getEmployeeById(empId);
    }

    @GetMapping("/empParam")
    public List<Employee> getEmployeeByIdParam(@RequestParam final String empId) {
        return service.getEmployeeByIdParam(empId);
    }

    @PostMapping("/createUpdateAction")
    public List<Employee> createUpdateEmployee(@RequestBody final Employee employee, @RequestParam final String action, @RequestParam(required = false) final String empId) {
        return service.createUpdateEmployee(employee, action, empId);
    }

    @PostMapping("/deleteAction")
    public List<Employee> deleteEmployee(@RequestParam final String empId) {
        return service.deleteEmployee(empId);
    }
}
