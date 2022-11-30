package com.example.employeeaccounting.service;

import com.example.employeeaccounting.exception.EmployeeAlreadyAddedException;
import com.example.employeeaccounting.exception.EmployeeNotFoundException;
import com.example.employeeaccounting.exception.EmployeeStorageIsFullException;
import com.example.employeeaccounting.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Integer SIZE = 2;

    private final List<Employee> employees = new ArrayList<>();

    @Override
    public Employee addEmployee(String firstName,
                                String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("This employee has already been added");
        }
        if (employees.size() <= SIZE) {
            employees.add(employee);
            return employee;
        } else {
            throw new EmployeeStorageIsFullException("The list of employees is full");
        }
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            employees.remove(employee);
            return employee;
        }
        throw new EmployeeNotFoundException("This employee is not in the list");
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            return employee;
        }
        throw new EmployeeNotFoundException("This employee is not in the list");
    }

    @Override
    public List<Employee> showEmployees() {
        return employees;
    }
}
