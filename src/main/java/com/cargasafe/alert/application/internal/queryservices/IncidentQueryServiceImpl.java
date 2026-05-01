package com.cargasafe.alert.application.internal.queryservices;

import com.cargasafe.alert.domain.model.entities.Incident;
import com.cargasafe.alert.domain.model.queries.GetIncidentsByAlertIdQuery;
import com.cargasafe.alert.domain.services.IncidentQueryService;
import com.cargasafe.alert.infrastructure.persistence.jpa.IncidentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidentQueryServiceImpl implements IncidentQueryService {

    private final IncidentRepository incidentRepository;

    public IncidentQueryServiceImpl(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    @Override
    public List<Incident> handle(GetIncidentsByAlertIdQuery query) {
        return incidentRepository.findByAlertId(query.alertId());
    }
}
