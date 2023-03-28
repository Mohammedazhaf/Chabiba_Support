package com.Chabiba_Support.Chabiba_Support.repositories;

import com.Chabiba_Support.Chabiba_Support.models.Client;
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
public interface ClientRepository extends JpaRepository<Client, Long> {

        void deleteClientById(Long idPersonne);
        Optional<Client> findClientById(Long idPersonne);

        Client findClientByEmail(String email);

        @Query("SELECT c FROM Client c WHERE c.nomEntreprise = :nomEntreprise")
        List<Client> findClientByNomEntreprise(@Param("nomEntreprise") String nomEntreprise);

        @Query("SELECT c FROM Client c WHERE LOWER(c.nom) LIKE CONCAT('%', LOWER(:nom), '%')")
        List<Client> findByNomLikeIgnoreCase(@Param("nom") String nom);
    }

