package com.Chabiba_Support.Chabiba_Support.repositories;

import com.Chabiba_Support.Chabiba_Support.models.Affectation;
import com.Chabiba_Support.Chabiba_Support.models.Demande;
import com.Chabiba_Support.Chabiba_Support.models.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface AffectationRepository extends JpaRepository<Affectation, Long> {
    List<Affectation> findAll();

//    Optional<Affectation> findByIdDemande(Long demandeId);



    List<Affectation> findByDemande(Demande demande);

    List<Affectation> findByEmployee(Employee employee);

    Affectation save(Affectation affectation);

    void delete(Affectation affectation);
}
