package com.example.employeeaccounting.service;

import com.example.employeeaccounting.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee addEmployee(String firstName, String LastName, double salary, int department);
    Employee removeEmployee(String firstName, String LastName);
    Employee findEmployee(String firstName, String LastName);

    List<Employee> showEmployees();

}
