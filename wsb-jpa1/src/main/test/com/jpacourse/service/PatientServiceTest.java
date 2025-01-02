package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.persistence.enums.Specialization;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientServiceTest
{
    @Autowired
    private PatientDao patientDao;

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    private PatientService patientService;

    @Transactional
    @Test
    public void testShouldDeletePatientAndCascadeVisits() {
        // given
        DoctorEntity doctor = new DoctorEntity();
        doctor.setFirstName("John");
        doctor.setLastName("Doe");
        doctor.setTelephoneNumber("123456789");
        doctor.setDoctorNumber("DOC001");
        doctor.setSpecialization(Specialization.SURGEON);
        doctor = doctorDao.save(doctor);

        PatientEntity patient = new PatientEntity();
        patient.setFirstName("Jane");
        patient.setLastName("Smith");
        patient.setTelephoneNumber("987654321");
        patient.setDateOfBirth(LocalDateTime.now().toLocalDate());
        patient.setPatientNumber("PAT001");
        patient.setInsured(true);
        patient = patientDao.save(patient);

        VisitEntity visit = new VisitEntity();
        visit.setDescription("Regular Checkup");
        visit.setTime(LocalDateTime.now());
        visit.setDoctor(doctor);
        visit.setPatient(patient);

        patient.getVisits().add(visit);
        patient = patientDao.save(patient);

        // when
        patientService.delete(patient.getId());

        // then
        PatientEntity deletedPatient = patientDao.findOne(patient.getId());
        assertThat(deletedPatient).isNull();

        List<VisitEntity> visits = doctor.getVisits();
        assertThat(visits).isEmpty();

        DoctorEntity persistedDoctor = doctorDao.findOne(doctor.getId());
        assertThat(persistedDoctor).isNotNull();
    }

    @Transactional
    @Test
    public void testShouldFindPatientByIdWithTO() {
        // given
        PatientEntity patient = new PatientEntity();
        patient.setFirstName("Jane");
        patient.setLastName("Smith");
        patient.setTelephoneNumber("987654321");
        patient.setDateOfBirth(LocalDateTime.now().toLocalDate());
        patient.setPatientNumber("PAT001");
        patient.setInsured(true);
        patient = patientDao.save(patient);

        // when
        PatientTO patientTO = patientService.findById(patient.getId());

        // then
        assertThat(patientTO).isNotNull();
        assertThat(patientTO.getFirstName()).isEqualTo("Jane");
        assertThat(patientTO.getLastName()).isEqualTo("Smith");
        assertThat(patientTO.getTelephoneNumber()).isEqualTo("987654321");
        assertThat(patientTO.getDateOfBirth()).isEqualTo(patient.getDateOfBirth());
        assertThat(patientTO.isInsured()).isTrue();
    }
}
