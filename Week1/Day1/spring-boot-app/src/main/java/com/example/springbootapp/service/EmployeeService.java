package com.example.springbootapp.service;

import com.example.springbootapp.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    public List<Employee> getEmployeeDetails() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("1234", "abc"));
        employees.add(new Employee("4567", "xyz"));
        return employees;
    }

    public List<Employee> getEmployeeById(final String empId) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("1234", "abc"));
        employees.add(new Employee("4567", "xyz"));
        return employees.stream().filter(employee -> employee.getEmpId().equals(empId)).collect(Collectors.toList());
    }

    public List<Employee> getEmployeeByIdParam(final String empId) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("1234", "abc"));
        employees.add(new Employee("4567", "xyz"));
        return employees.stream().filter(employee -> employee.getEmpId().equals(empId)).collect(Collectors.toList());
    }

    public List<Employee> createUpdateEmployee(final Employee employee, final String action, final String empId) {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("1234", "abc"));
        employeeList.add(new Employee("4567", "xyz"));

        if (action.equals("create")) {
            employeeList.add(employee);
        } else if (action.equals("update")) {
            List<Employee> empList = employeeList.stream().filter(emp -> emp.getEmpId().equals(empId)).collect(Collectors.toList());
            Integer index = employeeList.indexOf(empList.get(0));
            if (!index.equals(-1) && index != null) {
                employeeList.set(index, employee);
            }
        }
        return employeeList;
    }

    public List<Employee> deleteEmployee(final String empId) {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("1234", "abc"));
        employeeList.add(new Employee("4567", "xyz"));
        List<Employee> empList = employeeList.stream().filter(emp -> emp.getEmpId().equals(empId)).collect(Collectors.toList());
        Integer index = employeeList.indexOf(empList.get(0));
        if (!index.equals(-1) && index != null) {
            employeeList.remove(index.intValue());
        }
        return employeeList;
    }
}
