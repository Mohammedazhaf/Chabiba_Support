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
    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }

    public Employee findEmployeeByIdPersonne(Long idPersonne) {
        return employeeRepository.findEmployeeByIdPersonne(idPersonne).orElseThrow(() -> new EmployeeNotFoundException("Employee by id " + idPersonne + " was not found"));
    }

    public List<Employee> findByNomLikeIgnoreCase(String nom) {
        return employeeRepository.findByNomLikeIgnoreCase(nom);
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public long countAllEmployees() {
        return employeeRepository.count();
    }

    public Boolean CheckIfRoleExists(String role) {
        EmployeeRole employeeRole = EmployeeRole.valueOf(role);
        List<Employee> ListEmployee  = employeeRepository.findEmployeeByRole(employeeRole);
        if (ListEmployee.size()>0)
            return true;
        else
            return false;
    }







}
