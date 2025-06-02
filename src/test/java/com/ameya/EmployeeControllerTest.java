package com.ameya;

import com.ameya.controller.EmployeeController;
import com.ameya.entity.Employee;
import com.ameya.service.EmployeeInfoBusinessService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private EmployeeInfoBusinessService employeeService;

    @Test
    void testGetEmployeesByDepartmentSortedByName() throws Exception {
        List<Employee> mockList = List.of(new Employee(1003L, "Ankit"));
        Mockito.when(employeeService.getEmployeesByDepartmentSortedByName("HR"))
                .thenReturn(mockList);
        mockMvc.perform(get("/employees/by-department/HR"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Ankit"));
    }

    @Test
    void testGetEmployeesStartingWithA() throws Exception {
        List<Employee> mockList = List.of(
                new Employee(1001L, "Alice"),
                new Employee(1002L, "Amanda")
        );

        Mockito.when(employeeService.getEmployeesStartingWithA("HR"))
                .thenReturn(mockList);

        mockMvc.perform(get("/employees/department/HR/starting-with-a"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Alice"))
                .andExpect(jsonPath("$[1].name").value("Amanda"));
    }
    @Test
    void testGetAllEmployeesSortedByDepartmentAndEmpNo() throws Exception {
        List<Employee> mockList = List.of(
                new Employee(1003L, "Bob"),
                new Employee(1004L, "Charlie")
        );

        Mockito.when(employeeService.getAllEmployeesSortedByDeptAndEmpNo())
                .thenReturn(mockList);

        mockMvc.perform(get("/employees/sorted-by-department-and-id"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Bob"))
                .andExpect(jsonPath("$[1].name").value("Charlie"));
    }



}
