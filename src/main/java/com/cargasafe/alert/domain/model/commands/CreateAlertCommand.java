package com.cargasafe.alert.domain.model.commands;

import com.cargasafe.alert.domain.model.valueobjects.AlertType;
import com.cargasafe.alert.domain.model.valueobjects.NotificationChannel;

public record CreateAlertCommand(
        Long deliveryOrderId,
        AlertType alertType,
        String description,
        NotificationChannel notificationChannel,
        String message) {
}
