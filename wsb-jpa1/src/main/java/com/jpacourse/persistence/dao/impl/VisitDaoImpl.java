package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.VisitEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class VisitDaoImpl extends AbstractDao<VisitEntity, Long> implements VisitDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<VisitEntity> findByPatientId(Long patientId) {
        return entityManager.createQuery(
                "SELECT v FROM VisitEntity v WHERE v.patient.id = :patientId",
                VisitEntity.class
            )
            .setParameter("patientId", patientId)
            .getResultList();
    }
}
