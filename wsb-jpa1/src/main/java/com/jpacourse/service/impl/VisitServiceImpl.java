package com.jpacourse.service;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.mapper.VisitMapper;
import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.VisitEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

@Service
@Transactional
public class VisitServiceImpl implements VisitService {

    private final VisitDao visitDao;

    @Autowired
    public VisitServiceImpl(VisitDao pVisitDao) {
        visitDao = pVisitDao;
    }

    @Override
    public List<VisitTO> findVisitsByPatientId(Long patientId) {
        List<VisitEntity> visits = visitDao.findByPatientId(patientId);
        List<VisitTO> visitsTO = new ArrayList<>();
        for (VisitEntity visit : visits) {
            visitsTO.add(VisitMapper.mapToTO(visit));
        }
        return visitsTO;
    }
}