package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.VisitEntity;

import java.util.List;

public interface VisitDao {
    List<VisitEntity> findByPatientId(Long patientId);
}
