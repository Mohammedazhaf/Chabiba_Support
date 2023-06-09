package com.Chabiba_Support.Chabiba_Support.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.Chabiba_Support.Chabiba_Support.models.Affectation;
import com.Chabiba_Support.Chabiba_Support.repositories.AffectationRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManagerFactory;

import java.sql.Date;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionDelegatorBaseImpl;
import org.hibernate.graph.internal.RootGraphImpl;
import org.hibernate.graph.spi.RootGraphImplementor;
import org.hibernate.jpa.internal.JpaComplianceImpl;
import org.hibernate.metamodel.model.domain.internal.EntityTypeImpl;
import org.hibernate.metamodel.model.domain.internal.JpaMetamodelImpl;
import org.hibernate.type.descriptor.java.DbTimestampJavaType;
import org.hibernate.type.spi.TypeConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {AffectationService.class})
@ExtendWith(MockitoExtension.class)
public class AffectationServiceTest {

    @Mock
    private EntityManagerFactory entityManagerFactory;

    @Mock
    private AffectationRepository affectationRepository;

    @Mock
    private EntityManager entityManager;
    @Mock
    private EntityGraph<?> entityGraph;

    @InjectMocks
    private AffectationService affectationService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllAffectations() {
        // Create a list of dummy affectations
        List<Affectation> dummyAffectations = new ArrayList<>();
        dummyAffectations.add(new Affectation());
        dummyAffectations.add(new Affectation());

        // Mock the repository's findAll method to return the dummy affectations
        when(affectationRepository.findAll()).thenReturn(dummyAffectations);

        // Call the service method
        List<Affectation> result = affectationService.getAll();

        // Verify that the repository's findAll method was called once
        verify(affectationRepository, times(1)).findAll();

        // Verify the size of the result list
        assertEquals(2, result.size());
    }

    @Test
    public void testSaveAffectation() {
        // Create a dummy affectation
        Affectation dummyAffectation = new Affectation();

        // Mock the repository's save method to return the dummy affectation
        when(affectationRepository.save(dummyAffectation)).thenReturn(dummyAffectation);

        // Call the service method
        Affectation result = affectationService.saveAffectation(dummyAffectation);

        // Verify that the repository's save method was called once
        verify(affectationRepository, times(1)).save(dummyAffectation);

        // Verify the result object
        assertEquals(dummyAffectation, result);
    }
}
