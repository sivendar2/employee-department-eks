package com.ameya.service;

import com.ameya.entity.Employee;
import com.ameya.repository.DepartmentRepository;
import com.ameya.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeInfoBusinessService {

    private final EmployeeRepository employeeRepo;
    private final DepartmentRepository departmentRepo;

    public EmployeeInfoBusinessService(EmployeeRepository employeeRepo, DepartmentRepository departmentRepo) {
        this.employeeRepo = employeeRepo;
        this.departmentRepo = departmentRepo;
    }

    public List<Employee> getEmployeesByDepartmentSortedByEmpNo(String departmentName) {
        return employeeRepo.findByDepartment_NameOrderByEmployeeNumber(departmentName);
    }

    public List<Employee> getEmployeesStartingWithA(String departmentName) {
        return employeeRepo.findByDepartment_NameAndNameStartingWithIgnoreCase(departmentName, "A");
    }

    public List<Employee> getAllEmployeesSortedByDeptAndEmpNo() {
        return employeeRepo.findAllByOrderByDepartment_NameAscEmployeeNumberAsc();
    }

    public List<Employee> getEmployeesByDepartmentSortedByName(String departmentName) {
        return employeeRepo.findByDepartment_NameOrderByNameAsc(departmentName);
    }

    public String getDepartmentNameByEmployeeNumber(Long employeeNumber) {
        return employeeRepo.findByEmployeeNumber(employeeNumber)
                .map(emp -> emp.getDepartment().getName())
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }
}

