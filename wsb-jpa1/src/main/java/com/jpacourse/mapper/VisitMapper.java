package com.jpacourse.mapper;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.entity.VisitEntity;

import java.util.stream.Collectors;

public final class VisitMapper
{

    public static VisitTO mapToTO(final VisitEntity visitEntity) {
        if (visitEntity == null) {
            return null;
        }

        VisitTO visitTO = new VisitTO();
        visitTO.setId(visitEntity.getId());
        visitTO.setDescription(visitEntity.getDescription());
        visitTO.setTime(visitEntity.getTime());

        if (visitEntity.getDoctor() != null) {
            visitTO.setDoctorName(visitEntity.getDoctor().getFirstName() + " " + visitEntity.getDoctor().getLastName());
        }

        if (visitEntity.getMedicalTreatments() != null) {
            visitTO.setMedicalTreatments(visitEntity.getMedicalTreatments().stream()
                    .map(MedicalTreatmentMapper::mapToTO) // Assuming a MedicalTreatmentMapper exists
                    .collect(Collectors.toList()));
        }

        return visitTO;
    }

    public static VisitEntity mapToEntity(final VisitTO visitTO) {
        if (visitTO == null) {
            return null;
        }

        VisitEntity visitEntity = new VisitEntity();
        visitEntity.setId(visitTO.getId());
        visitEntity.setDescription(visitTO.getDescription());
        visitEntity.setTime(visitTO.getTime());

        // Doctor and MedicalTreatments are not mapped back in this example, as it may depend on specific logic

        return visitEntity;
    }
}
