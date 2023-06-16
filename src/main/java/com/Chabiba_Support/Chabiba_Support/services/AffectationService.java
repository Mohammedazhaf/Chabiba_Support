package com.Chabiba_Support.Chabiba_Support.services;

import com.Chabiba_Support.Chabiba_Support.models.Affectation;
import com.Chabiba_Support.Chabiba_Support.repositories.AffectationRepository;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AffectationService {
    @Autowired
    private AffectationRepository affectationRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Affectation> getAll(){
        return affectationRepository.findAll();
    }


    public Affectation saveAffectation(Affectation affectation){
        return affectationRepository.save(affectation);
    }
    public Affectation getByIdAffectation(long id){
        return affectationRepository.getById(id);
    }

    public Affectation getAffectationById(long id) {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("affectationGraph");
        Map<String, Object> hints = new HashMap<>();
        hints.put("javax.persistence.fetchgraph", entityGraph);
        return entityManager.find(Affectation.class, id, hints);
    }
	public void deleteAffectation(Affectation currentAffectation) {
		affectationRepository.delete(currentAffectation);
	}


}
