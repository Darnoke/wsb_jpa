package com.jpacourse.persistance.dao;

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
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientDaoTest {

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private DoctorDao doctorDao;

    @Transactional
    @Test
    public void testShouldAddVisitToPatient() {
        // given
        PatientEntity patient = new PatientEntity();
        patient.setFirstName("Konrad");
        patient.setLastName("Woźniak");
        patient.setTelephoneNumber("111222111");
        patient.setDateOfBirth(LocalDateTime.now().toLocalDate());
        patient.setPatientNumber("99974");
        patient.setInsured(true);
        patient = patientDao.save(patient);

        DoctorEntity doctor = new DoctorEntity();
        doctor.setFirstName("Aleksander");
        doctor.setLastName("Okrasa");
        doctor.setTelephoneNumber("333444222");
        doctor.setDoctorNumber("100154");
        doctor.setSpecialization(Specialization.SURGEON);
        doctor = doctorDao.save(doctor);

        // when
        patientDao.addVisitToPatient(patient.getId(), doctor.getId(), LocalDateTime.now(), "Walidacja testów");

        // then
        PatientEntity updatedPatient = patientDao.findOne(patient.getId());
        assertThat(updatedPatient.getVisits()).hasSize(1);
        VisitEntity visit = updatedPatient.getVisits().get(0);
        assertThat(visit.getDescription()).isEqualTo("Walidacja testów");
        assertThat(visit.getDoctor().getId()).isEqualTo(doctor.getId());
    }

    @Test
    public void testShouldFindPatientsByLastName() {
        // given
        String lastName = "Doe";

        // when
        List<PatientEntity> patients = patientDao.findByLastName(lastName);

        // then
        assertFalse(patients.isEmpty(), "No patients found with last name 'Doe'");
        assertTrue(patients.stream().allMatch(p -> p.getLastName().equalsIgnoreCase(lastName)));
    }

    @Test
    public void testShouldFindPatientsWithMoreThanXVisits() {
        // given
        Long visitCount = 2L;

        // when
        List<PatientEntity> patients = patientDao.findPatientsWithMoreThanXVisits(visitCount);

        // then
        assertThat(patients).isNotEmpty();
        assertThat(patients).allSatisfy(patient ->
            assertThat(patient.getVisits().size()).isGreaterThan(visitCount.intValue())
        );
    }
}