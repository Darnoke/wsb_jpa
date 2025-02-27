package com.jpacourse.service;

import com.jpacourse.dto.VisitTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VisitServiceTest {
    @Autowired
    private VisitService visitService;

    @Test
    public void testShouldFindVisitsByPatientId() {
        // given
        Long patientId = 1L;

        // when
        List<VisitTO> visits = visitService.findVisitsByPatientId(patientId);

        // then
        assertFalse(visits.isEmpty(), "No visits found for patient ID " + patientId);

        assertThat(visits, everyItem(hasProperty("patientId", equalTo(patientId))));
    }

}
