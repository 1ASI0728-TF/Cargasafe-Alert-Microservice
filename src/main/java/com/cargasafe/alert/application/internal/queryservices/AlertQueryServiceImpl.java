package com.cargasafe.alert.application.internal.queryservices;

import com.cargasafe.alert.domain.model.aggregates.Alert;
import com.cargasafe.alert.domain.model.queries.GetAlertByIdQuery;
import com.cargasafe.alert.domain.model.queries.GetAlertsByStatusQuery;
import com.cargasafe.alert.domain.model.queries.GetAlertsByTypeQuery;
import com.cargasafe.alert.domain.model.queries.GetAllAlertsQuery;
import com.cargasafe.alert.domain.services.AlertQueryService;
import com.cargasafe.alert.infrastructure.persistence.jpa.AlertRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlertQueryServiceImpl implements AlertQueryService {

    private final AlertRepository alertRepository;

    public AlertQueryServiceImpl(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    @Override
    public Optional<Alert> handle(GetAlertByIdQuery query) {
        return alertRepository.findById(query.alertId());
    }

    @Override
    public List<Alert> handle(GetAllAlertsQuery query) {
        return alertRepository.findAll();
    }

    @Override
    public List<Alert> handle(GetAlertsByTypeQuery query) {
        return alertRepository.findByAlertType(query.type());
    }

    @Override
    public List<Alert> handle(GetAlertsByStatusQuery query) {
        return alertRepository.findByAlertStatus(query.status());
    }
}
