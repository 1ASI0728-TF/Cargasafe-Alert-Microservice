package com.cargasafe.alert.domain.services;

import com.cargasafe.alert.domain.model.entities.Incident;
import com.cargasafe.alert.domain.model.queries.GetIncidentsByAlertIdQuery;

import java.util.List;

public interface IncidentQueryService {

    List<Incident> handle(GetIncidentsByAlertIdQuery query);
}
