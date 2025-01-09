package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitTime, String description) {
        PatientEntity patient = entityManager.find(PatientEntity.class, patientId);
        DoctorEntity doctor = entityManager.find(DoctorEntity.class, doctorId);

        if (patient != null && doctor != null) {
            VisitEntity visit = new VisitEntity();
            visit.setPatient(patient);
            visit.setDoctor(doctor);
            visit.setTime(visitTime);
            visit.setDescription(description);

            patient.getVisits().add(visit);
            entityManager.merge(patient);
        }
    }

    @Override
    public List<PatientEntity> findByLastName(String lastName) {
        return entityManager.createQuery(
                "SELECT p FROM PatientEntity p WHERE LOWER(p.lastName) LIKE LOWER(:lastName)",
                PatientEntity.class
            )
            .setParameter("lastName", lastName)
            .getResultList();
    }

    @Override
    public List<PatientEntity> findPatientsWithMoreThanXVisits(Long visitCount) {
        return entityManager.createQuery(
                "SELECT p FROM PatientEntity p LEFT JOIN FETCH p.visits v " +
                "WHERE (SELECT COUNT(v1) FROM VisitEntity v1 WHERE v1.patient = p) > :visitCount",
                PatientEntity.class
            )
            .setParameter("visitCount", visitCount)
            .getResultList();
    }

    @Override
    public List<PatientEntity> findByInsured(boolean isInsured) {
        // Dodane pole to isInsured jest typu boolean, więc ciezko zlozyc bardziej skomplikowane zapytanie
        return entityManager.createQuery(
                "SELECT p FROM PatientEntity p WHERE p.isInsured = :isInsured",
                PatientEntity.class
            )
            .setParameter("isInsured", isInsured)
            .getResultList();
    }
}