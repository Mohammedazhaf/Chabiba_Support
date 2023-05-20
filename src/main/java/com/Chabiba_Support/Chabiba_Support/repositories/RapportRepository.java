package com.Chabiba_Support.Chabiba_Support.repositories;


import com.Chabiba_Support.Chabiba_Support.models.Demande;
import com.Chabiba_Support.Chabiba_Support.models.Rapport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface RapportRepository extends JpaRepository<Rapport, Long> {

    List<Rapport> findAll();
    Optional<Rapport> findByIdRapport(Long rapportId);

    List<Rapport> findRapportByIdPersonne(Long idPersonne);

    List<Rapport> findByDemande(Demande demande);

    List<Rapport> findByDate(Date date);

    Rapport save(Rapport rapport);

    void delete(Rapport rapport);


}
