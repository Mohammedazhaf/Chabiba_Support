package com.Chabiba_Support.Chabiba_Support.repositories;


import com.Chabiba_Support.Chabiba_Support.models.Employee;
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

    Optional<Employee> findEmployeeByIdPersonne(Long idPersonne);



    @Query("SELECT e FROM Employee e WHERE e.role = :role")
    List<Employee> findEmployeeByRole(@Param("role") Role role);

    @Query("SELECT e FROM Employee e WHERE LOWER(e.nom) LIKE CONCAT('%', LOWER(:nom), '%')")
    List<Employee> findByNomLikeIgnoreCase(@Param("nom") String nom);

}