package com.cargasafe.alert.interfaces.rest.resources;

import com.cargasafe.alert.domain.model.valueobjects.NotificationChannel;

public record SendNotificationResource(
        Long alertId,
        NotificationChannel notificationChannel,
        String message
) {
}
