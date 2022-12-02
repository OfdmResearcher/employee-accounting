package com.example.employeeaccounting.service;

import com.example.employeeaccounting.exception.EmployeeAlreadyAddedException;
import com.example.employeeaccounting.exception.EmployeeNotFoundException;
import com.example.employeeaccounting.exception.EmployeeStorageIsFullException;
import com.example.employeeaccounting.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Integer LIMIT = 10;

    private final Map<String, Employee> employees = new HashMap<>();

    private final ValidatorService validatorService;

    public EmployeeServiceImpl(ValidatorService validatorService) {
        this.validatorService = validatorService;
    }

    private String getKey(String name, String surname) {
        return name + " - " + surname;
    }

    @Override
    public List<Employee> showEmployees() {
        return new ArrayList<>(employees.values());
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, double salary, int department) {
        Employee employee = new Employee(validatorService.validateName(firstName),
                validatorService.validateSurname(lastName),
                salary,
                department);
        String key = getKey(employee.getFirstName(), employee.getLastName());
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException("This employee has already been added");
        }
        if (employees.size() <= LIMIT) {
            employees.put(key, employee);
            return employee;
        } else {
            throw new EmployeeStorageIsFullException("The list of employees is full");
        }
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        firstName = validatorService.validateName(firstName);
        lastName = validatorService.validateSurname(lastName);
        String key = getKey(firstName, lastName);
        if (employees.containsKey(key)) {
            return employees.remove(key);
        }
        throw new EmployeeNotFoundException("This employee is not in the list");
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        firstName = validatorService.validateName(firstName);
        lastName = validatorService.validateSurname(lastName);
        String key = getKey(firstName, lastName);
        if (employees.containsKey(key)) {
            return employees.get(key);
        }
        throw new EmployeeNotFoundException("This employee is not in the list");
    }

}
