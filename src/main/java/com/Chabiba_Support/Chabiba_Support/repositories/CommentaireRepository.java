package com.Chabiba_Support.Chabiba_Support.repositories;

import com.Chabiba_Support.Chabiba_Support.models.Commentaire;
import com.Chabiba_Support.Chabiba_Support.models.Demande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;



public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {


    List<Commentaire> findByEtoile(int etoile);



    Commentaire save(Commentaire commentaire);
    List<Commentaire> findByDemande(Demande Demande);

    void delete(Commentaire commentaire);

    List<Commentaire> findAll();
}