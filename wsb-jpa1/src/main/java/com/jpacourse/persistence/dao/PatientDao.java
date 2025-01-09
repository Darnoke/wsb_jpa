package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.PatientEntity;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PatientDao extends Dao<PatientEntity, Long>
{
    void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitTime, String description);
    List<PatientEntity> findByLastName(String lastName);
    List<PatientEntity> findPatientsWithMoreThanXVisits(Long visitCount);
    List<PatientEntity> findByInsured(boolean isInsured);
}
