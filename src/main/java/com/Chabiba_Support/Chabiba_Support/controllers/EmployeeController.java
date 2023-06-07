package com.Chabiba_Support.Chabiba_Support.controllers;

import com.Chabiba_Support.Chabiba_Support.models.Employee;
import com.Chabiba_Support.Chabiba_Support.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/respo/employee",produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees () {
        List<Employee> employees = employeeService.findAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

//    @PostMapping("/add")
//    public ResponseEntity<Employee>addEmployee(@RequestBody Employee employee) {
//        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/controllers/employee/add").toUriString());
//        return ResponseEntity.created(uri).body(employeeService.addEmployee(employee));
//    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Employee> getEmployeeBYIdPersonne (@PathVariable("id") Long id) {
        Employee employee = employeeService.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }


    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {

        Employee updateUser = employeeService.updateEmployee(employee);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
        Employee employee = employeeService.findEmployeeById(id);
        employeeService.deleteEmployee(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/{nom}")
    public ResponseEntity<List<Employee>> getEmployeeByNomLikeIgnoreCase(@PathVariable String nom) {
        List<Employee> employees = employeeService.findByNomLikeIgnoreCase(nom);
        return  new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countAllUser() {
        long count = employeeService.countAllEmployees();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
