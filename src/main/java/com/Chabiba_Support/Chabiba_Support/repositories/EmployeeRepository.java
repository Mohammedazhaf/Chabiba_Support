package com.Chabiba_Support.Chabiba_Support.repositories;


import com.Chabiba_Support.Chabiba_Support.models.Employee;
import com.Chabiba_Support.Chabiba_Support.models.EmployeeRole;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    void deleteEmployeeById(Long idPersonne);
    Optional<Employee> findEmployeeById(Long idPersonne);
    Employee findEmployeeByEmail(String email);
    @Query("SELECT e FROM Employee e WHERE e.employeeRole = :role")
    List<Employee> findEmployeeByRole(@Param("role") EmployeeRole role);

    @Query("SELECT e FROM Employee e WHERE LOWER(e.nom) LIKE CONCAT('%', LOWER(:nom), '%')")
    List<Employee> findByNomLikeIgnoreCase(@Param("nom") String nom);

}