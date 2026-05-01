package com.cargasafe.alert.domain.services;

import com.cargasafe.alert.domain.model.commands.CreateIncidentFromAlertCommand;
import com.cargasafe.alert.domain.model.entities.Incident;

import java.util.Optional;

public interface IncidentCommandService {

    Optional<Incident> handle(CreateIncidentFromAlertCommand command);
}
