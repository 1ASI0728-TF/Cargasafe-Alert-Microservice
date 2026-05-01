package com.cargasafe.alert.interfaces.rest.resources;

import com.cargasafe.alert.domain.model.valueobjects.AlertType;
import com.cargasafe.alert.domain.model.valueobjects.NotificationChannel;

public record CreateAlertResource(
        Long deliveryOrderId,
        AlertType alertType,
        String description,
        NotificationChannel notificationChannel,
        String message
) {
}
