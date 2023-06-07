package com.Chabiba_Support.Chabiba_Support.repositories;

import com.Chabiba_Support.Chabiba_Support.models.Client;
import com.Chabiba_Support.Chabiba_Support.models.Demande;
import com.Chabiba_Support.Chabiba_Support.models.Etat;
import com.Chabiba_Support.Chabiba_Support.models.Personne;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface DemandeRepository extends JpaRepository<Demande, Long> {

    List<Demande> findByEtat(Etat etat);
//    List<Demande> findByClientIdPersonne(Long idPersoone);
    List<Demande> findByClient(Client client);
}
