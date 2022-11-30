package com.example.employeeaccounting.service;

import com.example.employeeaccounting.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    Employee findEmployeeWithMaxSalaryByDepartment(int departmentId);

    Employee findEmployeeWithMinSalaryByDepartment(int departmentId);

    List<Employee> findAllEmployeesByDepartment(int departmentId);

    Map<Integer, List<Employee>> findAllEmployeesByDepartments();
}
