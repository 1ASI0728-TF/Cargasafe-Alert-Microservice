package com.cargasafe.alert.domain.services;

import com.cargasafe.alert.domain.model.entities.Notification;
import com.cargasafe.alert.domain.model.queries.GetNotificationsByAlertIdQuery;

import java.util.List;

public interface NotificationQueryService {

    List<Notification> handle(GetNotificationsByAlertIdQuery query);
}
