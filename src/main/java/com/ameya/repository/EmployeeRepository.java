package com.ameya.repository;

import com.ameya.entity.Department;
import com.ameya.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByDepartment_NameOrderByEmployeeNumber(String departmentName);
    List<Employee> findByDepartment_NameAndNameStartingWithIgnoreCase(String departmentName, String prefix);
    List<Employee> findAllByOrderByDepartment_NameAscEmployeeNumberAsc();
    List<Employee> findByDepartment_NameOrderByNameAsc(String departmentName);
    Optional<Employee> findByEmployeeNumber(Long employeeNumber);
}


