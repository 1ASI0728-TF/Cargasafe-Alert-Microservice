package com.cargasafe.alert.interfaces.rest.resources;

public record CreateIncidentFromAlertResource(
        Long alertId,
        String description
) {
}
