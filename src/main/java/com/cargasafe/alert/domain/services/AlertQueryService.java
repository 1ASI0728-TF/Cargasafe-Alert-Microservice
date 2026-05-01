package com.cargasafe.alert.domain.services;

import com.cargasafe.alert.domain.model.aggregates.Alert;
import com.cargasafe.alert.domain.model.queries.GetAlertByIdQuery;
import com.cargasafe.alert.domain.model.queries.GetAlertsByStatusQuery;
import com.cargasafe.alert.domain.model.queries.GetAlertsByTypeQuery;
import com.cargasafe.alert.domain.model.queries.GetAllAlertsQuery;

import java.util.List;
import java.util.Optional;

public interface AlertQueryService {

    Optional<Alert> handle(GetAlertByIdQuery query);

    List<Alert> handle(GetAllAlertsQuery query);

    List<Alert> handle(GetAlertsByTypeQuery query);

    List<Alert> handle(GetAlertsByStatusQuery query);
}
