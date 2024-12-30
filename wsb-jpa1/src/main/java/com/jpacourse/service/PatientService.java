package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;

public interface PatientService
{
    PatientTO findById(final Long id);
    void delete(final Long id);
}
