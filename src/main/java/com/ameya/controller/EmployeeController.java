package com.ameya.controller;

import com.ameya.entity.Employee;
import com.ameya.service.EmployeeInfoBusinessService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeInfoBusinessService employeeService;

    public EmployeeController(EmployeeInfoBusinessService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/by-department/{departmentName}")
    public List<Employee> getEmployeesByDepartmentSortedByName(@PathVariable String departmentName) {
        return employeeService.getEmployeesByDepartmentSortedByName(departmentName);
    }


    //Retrieve department name by employee number
    @GetMapping("/{employeeNumber}/department")
    public String getDepartmentByEmployeeNumber(@PathVariable Long employeeNumber) {
        return employeeService.getDepartmentNameByEmployeeNumber(employeeNumber);
    }

    // Additional endpoints (optional)
    @GetMapping("/department/{departmentName}/sorted-by-id")
    public List<Employee> getEmployeesByDepartmentSortedByEmpNo(@PathVariable String departmentName) {
        return employeeService.getEmployeesByDepartmentSortedByEmpNo(departmentName);
    }

    @GetMapping("/department/{departmentName}/starting-with-a")
    public List<Employee> getEmployeesStartingWithA(@PathVariable String departmentName) {
        return employeeService.getEmployeesStartingWithA(departmentName);
    }

    @GetMapping("/sorted-by-department-and-id")
    public List<Employee> getAllEmployeesSortedByDepartmentAndEmpNo() {
        return employeeService.getAllEmployeesSortedByDeptAndEmpNo();
    }
}
