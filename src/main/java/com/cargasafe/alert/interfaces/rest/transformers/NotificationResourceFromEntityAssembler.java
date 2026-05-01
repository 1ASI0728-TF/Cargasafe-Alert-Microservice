package com.cargasafe.alert.interfaces.rest.transformers;

import com.cargasafe.alert.domain.model.entities.Notification;
import com.cargasafe.alert.interfaces.rest.resources.NotificationResource;

public class NotificationResourceFromEntityAssembler {

    public static NotificationResource toResourceFromEntity(Notification notification) {
        return new NotificationResource(
                notification.getId(),
                notification.getAlert().getId(),
                notification.getNotificationChannel(),
                notification.getMessage(),
                notification.getSentAt()
        );
    }
}
