package com.Chabiba_Support.Chabiba_Support.services;

import com.Chabiba_Support.Chabiba_Support.exception.EmployeeNotFoundException;
import com.Chabiba_Support.Chabiba_Support.models.Employee;
import com.Chabiba_Support.Chabiba_Support.models.Personne;
import com.Chabiba_Support.Chabiba_Support.models.Role;
import com.Chabiba_Support.Chabiba_Support.repositories.EmployeeRepository;
import com.Chabiba_Support.Chabiba_Support.repositories.PersonneRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PersonneRepository personneRepository;
    private  final PasswordEncoder passwordEncoder;


    public Employee addEmployee(Personne personne, String cin) {
        Personne savedPersonne = personneRepository.save(personne);
        Employee employee = new Employee(savedPersonne, cin);
        return employeeRepository.save(employee);
    }

    //la liste de tout les utilisateurs
    public List<Employee> findAllEmployees() {
        log.info("Fetching all employees");
        return employeeRepository.findAll();
    }

    //supprimer un utilisateur
    public void deleteEmployee(Employee employee) {
        Personne personne = personneRepository.findById(employee.getPersonne().getIdPersonne()).orElse(null);
        employeeRepository.delete(employee);
        personneRepository.deleteById(personne.getIdPersonne());
    }

    public Employee findEmployeeByIdPersonne(Long idPersonne) {
        Personne findedPersonne = personneRepository.findById(idPersonne).orElse(null);
        return employeeRepository.findEmployeeByPersonne(findedPersonne).orElseThrow(() -> new EmployeeNotFoundException("Employee by id " + idPersonne + " was not found"));
    }

    public List<Employee> findByNomLikeIgnoreCase(String nom) {
        return employeeRepository.findByNomLikeIgnoreCase(nom);
    }

    public Employee updateEmployee(Employee employee) {
        Long EmployeeId = employee.getIdEmployee();
        Long personneId = employee.getPersonne().getIdPersonne();

        Employee existenEmployee = employeeRepository.findById(EmployeeId).orElseThrow(() -> new RuntimeException("Client not found with ID: " + EmployeeId));

        // Update the necessary fields
        existenEmployee.setCin(employee.getCin());
        Personne personne = personneRepository.findById(personneId)
                .orElseThrow(() -> new RuntimeException("Personne not found with ID: " + personneId));
        personne.setNom(employee.getPersonne().getNom());
        personne.setPrenom(employee.getPersonne().getPrenom());
        personne.setEmail(employee.getPersonne().getEmail());
        personne.setNumTel(employee.getPersonne().getNumTel());
        personne.setMotDePasse(passwordEncoder.encode(employee.getPersonne().getMotDePasse()));
        personne.setRole(employee.getPersonne().getRole());

        Personne updatedPersonne= personneRepository.save(personne);
        existenEmployee.setPersonne(updatedPersonne);

        return employeeRepository.save(existenEmployee);
    }

    public long countAllEmployees() {
        return employeeRepository.count();
    }

    public Boolean CheckIfRoleExists(String role) {
        Role employeeRole = Role.valueOf(role);
        List<Employee> ListEmployee  = employeeRepository.findEmployeeByRole(employeeRole);
        if (ListEmployee.size()>0)
            return true;
        else
            return false;
    }

    public Employee findEmployeeById(Long id){
        return employeeRepository.findEmployeeById(id);
    }
}
