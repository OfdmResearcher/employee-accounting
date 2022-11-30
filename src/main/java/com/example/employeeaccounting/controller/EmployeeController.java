package com.example.employeeaccounting.controller;

import com.example.employeeaccounting.model.Employee;
import com.example.employeeaccounting.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(path="/employee")
@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> showEmployees() {
        return employeeService.showEmployees();
    }

    @GetMapping(path = "/add")
    public Employee addEmployee(@RequestParam(name = "firstName") String firstName,
                                @RequestParam(name = "lastName") String lastName) {
       return employeeService.addEmployee(firstName, lastName);
    }

    @GetMapping(path = "/remove")
    public Employee removeEmployee(@RequestParam(name = "firstName") String firstName,
                                @RequestParam(name = "lastName") String lastName) {
        return employeeService.removeEmployee(firstName, lastName);
    }

    @GetMapping(path = "/find")
    public Employee findEmployee(@RequestParam(name = "firstName") String firstName,
                                @RequestParam(name = "lastName") String lastName) {
        return employeeService.findEmployee(firstName, lastName);
    }
}
