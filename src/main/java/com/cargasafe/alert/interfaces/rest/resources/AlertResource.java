package com.cargasafe.alert.interfaces.rest.resources;

import com.cargasafe.alert.domain.model.valueobjects.AlertStatus;
import com.cargasafe.alert.domain.model.valueobjects.AlertType;

import java.util.List;

public record AlertResource(
        Long id,
        Long deliveryOrderId,
        AlertType alertType,
        AlertStatus alertStatus,
        List<IncidentResource> incidents,
        List<NotificationResource> notification
) {
}
