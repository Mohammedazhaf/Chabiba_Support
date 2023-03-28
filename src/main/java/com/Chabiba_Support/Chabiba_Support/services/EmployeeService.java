package com.Chabiba_Support.Chabiba_Support.services;

import com.Chabiba_Support.Chabiba_Support.exception.EmployeeNotFoundException;
import com.Chabiba_Support.Chabiba_Support.models.Employee;
import com.Chabiba_Support.Chabiba_Support.models.EmployeeRole;
import com.Chabiba_Support.Chabiba_Support.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class EmployeeService {
    private final EmployeeRepository employeeRepository;



    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    //la liste de tout les utilisateurs
    public List<Employee> findAllEmployees() {
        log.info("Fetching all employees");
        return employeeRepository.findAll();
    }

    //supprimer un utilisateur
    public void deleteEmployee(Long id){
        employeeRepository.deleteEmployeeById(id);
    }

    public Employee findEmployeeById(Long idPersonne) {
        return employeeRepository.findEmployeeById(idPersonne).orElseThrow(() -> new EmployeeNotFoundException("Employee by id " + idPersonne + " was not found"));
    }

    public List<Employee> findByNomLikeIgnoreCase(String nom) {
        return employeeRepository.findByNomLikeIgnoreCase(nom);
    }

    public Employee updateEmployee(Long idPersonne, String nom, String prenom, String numTel, String email,  String cin) {
        Employee employee = employeeRepository.findEmployeeById(idPersonne).orElse(null);
        if (employee == null) {
            return null;
        }
        employee.setNom(nom);
        employee.setPrenom(prenom);
        employee.setNumTel(numTel);
        employee.setEmail(email);
        employee.setCin(cin);

        return employeeRepository.save(employee);
    }

    public Boolean CheckIfRoleExists(String role) {
        EmployeeRole employeeRole = EmployeeRole.valueOf(role);
        List<Employee> ListEmployee  = employeeRepository.findEmployeeByRole(employeeRole);
        if (ListEmployee.size()>0)
            return true;
        else
            return false;
    }



    public Employee findEmployeeByEmail(String email) {
        return employeeRepository.findEmployeeByEmail(email);
    }



}
