package com.jpacourse.mapper;

import com.jpacourse.dto.MedicalTreatmentTO;
import com.jpacourse.persistence.entity.MedicalTreatmentEntity;
import com.jpacourse.persistence.enums.TreatmentType;

public final class MedicalTreatmentMapper
{

    public static MedicalTreatmentTO mapToTO(final MedicalTreatmentEntity medicalTreatmentEntity) {
        if (medicalTreatmentEntity == null) {
            return null;
        }

        MedicalTreatmentTO medicalTreatmentTO = new MedicalTreatmentTO();
        medicalTreatmentTO.setId(medicalTreatmentEntity.getId());
        medicalTreatmentTO.setDescription(medicalTreatmentEntity.getDescription());
        if (medicalTreatmentEntity.getType() != null) {
            medicalTreatmentTO.setTreatmentType(medicalTreatmentEntity.getType().name()); // Enum to String
        }

        return medicalTreatmentTO;
    }

    public static MedicalTreatmentEntity mapToEntity(final MedicalTreatmentTO medicalTreatmentTO) {
        if (medicalTreatmentTO == null) {
            return null;
        }

        MedicalTreatmentEntity medicalTreatmentEntity = new MedicalTreatmentEntity();
        medicalTreatmentEntity.setId(medicalTreatmentTO.getId());
        medicalTreatmentEntity.setDescription(medicalTreatmentTO.getDescription());
        if (medicalTreatmentTO.getTreatmentType() != null) {
            medicalTreatmentEntity.setType(Enum.valueOf(
                    TreatmentType.class, medicalTreatmentTO.getTreatmentType())); // String to Enum
        }

        return medicalTreatmentEntity;
    }
}
