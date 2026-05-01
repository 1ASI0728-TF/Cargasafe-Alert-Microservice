package com.cargasafe.alert.domain.services;

import com.cargasafe.alert.domain.model.commands.SendNotificationCommand;

public interface NotificationCommandService {

    void handle(SendNotificationCommand command);
}
