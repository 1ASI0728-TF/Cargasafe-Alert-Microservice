package com.cargasafe.alert.domain.model.commands;

public record CreateIncidentFromAlertCommand(Long alertId, String description) {
}
