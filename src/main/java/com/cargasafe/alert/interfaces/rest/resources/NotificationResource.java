package com.cargasafe.alert.interfaces.rest.resources;

import com.cargasafe.alert.domain.model.valueobjects.NotificationChannel;

import java.time.LocalDateTime;

public record NotificationResource(
        Long id,
        Long alertId,
        NotificationChannel notificationChannel,
        String message,
        LocalDateTime sentAt
) {
}
