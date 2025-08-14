package de.fherold.employee_management_api.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.fherold.employee_management_api.dto.EmployeeDto;
import de.fherold.employee_management_api.service.EmployeeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/test")
@Profile("dev") // Only active in dev profile
@RequiredArgsConstructor
public class TestDataController {

    private final EmployeeService employeeService;

    @PostMapping("/seed")
    public ResponseEntity<EmployeeDto> createTestData() {
        try {
            EmployeeDto testEmployee = new EmployeeDto(
                    null,
                    "Test",
                    "User",
                    "test@example.com");

            EmployeeDto savedEmployee = employeeService.createEmployee(testEmployee);
            return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("Error creating test data: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}