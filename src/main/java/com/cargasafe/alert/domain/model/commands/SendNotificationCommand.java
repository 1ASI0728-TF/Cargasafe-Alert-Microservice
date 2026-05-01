package com.cargasafe.alert.domain.model.commands;

import com.cargasafe.alert.domain.model.valueobjects.NotificationChannel;

public record SendNotificationCommand(Long alertId, NotificationChannel notificationChannel, String message) {
}
