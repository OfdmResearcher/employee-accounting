package com.example.employeeaccounting.service;

import com.example.employeeaccounting.exception.EmployeeNotFoundException;
import com.example.employeeaccounting.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee findEmployeeWithMaxSalaryByDepartment(int departmentId) {
        return employeeService.showEmployees()
                .stream()
                .filter(s-> s.getDepartment()==departmentId)
                .max(Comparator.comparingDouble(emp -> emp.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException("There is no any employee " +
                        "with max salary"));
    }

    @Override
    public Employee findEmployeeWithMinSalaryByDepartment(int departmentId) {
        return employeeService.showEmployees()
                .stream()
                .filter(s-> s.getDepartment()==departmentId)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("There is no any employee " +
                        "with min salary"));
    }

    @Override
    public List<Employee> findAllEmployeesByDepartment(int departmentId) {
        return employeeService.showEmployees()
                .stream()
                .filter(s -> s.getDepartment() == departmentId)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> findAllEmployeesByDepartments() {
        return employeeService.showEmployees()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
