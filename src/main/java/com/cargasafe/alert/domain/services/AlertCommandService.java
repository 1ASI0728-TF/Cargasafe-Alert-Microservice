package com.cargasafe.alert.domain.services;

import com.cargasafe.alert.domain.model.aggregates.Alert;
import com.cargasafe.alert.domain.model.commands.AcknowledgeAlertCommand;
import com.cargasafe.alert.domain.model.commands.CloseAlertCommand;
import com.cargasafe.alert.domain.model.commands.CreateAlertCommand;

import java.util.Optional;

public interface AlertCommandService {

    Optional<Alert> handle(CreateAlertCommand command);

    Optional<Alert> handle(AcknowledgeAlertCommand command);

    Optional<Alert> handle(CloseAlertCommand command);
}
