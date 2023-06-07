package com.Chabiba_Support.Chabiba_Support.repositories;


import com.Chabiba_Support.Chabiba_Support.models.Client;
import com.Chabiba_Support.Chabiba_Support.models.Employee;
import com.Chabiba_Support.Chabiba_Support.models.Personne;
import com.Chabiba_Support.Chabiba_Support.models.Role;
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

    Optional<Employee> findEmployeeByPersonne(Personne personne);
//
//
//
    @Query("SELECT e FROM Employee e JOIN e.personne p WHERE p.role = :role")
    List<Employee> findEmployeeByRole(@Param("role") Role role);

    @Query("SELECT e FROM Employee e JOIN e.personne p WHERE LOWER(p.nom) LIKE CONCAT('%', LOWER(:nom), '%')")
    List<Employee> findByNomLikeIgnoreCase(@Param("nom") String nom);

    @Query("SELECT e FROM Employee e JOIN FETCH e.personne WHERE e.idEmployee = :id")
    Employee findEmployeeById(@Param("id") Long id);

}