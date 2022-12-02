package com.example.employeeaccounting.controller;


import com.example.employeeaccounting.model.Employee;
import com.example.employeeaccounting.service.DepartmentService;
import com.example.employeeaccounting.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/max-salary")
    public Employee findEmployeeWithMaxSalaryByDepartment(@RequestParam(name = "departmentId") int departmentId) {
        return departmentService.findEmployeeWithMaxSalaryByDepartment(departmentId);
    }

    @GetMapping(path = "/min-salary")
    public Employee findEmployeeWithMinSalaryByDepartment(@RequestParam(name = "departmentId") int departmentId) {
        return departmentService.findEmployeeWithMinSalaryByDepartment(departmentId);
    }

    @GetMapping(path = "/all", params = "departmentId")
    public List<Employee> findAllEmployeesByDepartment(@RequestParam(name = "departmentId") int departmentId) {
        return departmentService.findAllEmployeesByDepartment(departmentId);
    }

    @GetMapping(path = "/all")
    public Map<Integer, List<Employee>> findAllEmployeesByDepartments() {
        return departmentService.findAllEmployeesByDepartments();
    }
}
