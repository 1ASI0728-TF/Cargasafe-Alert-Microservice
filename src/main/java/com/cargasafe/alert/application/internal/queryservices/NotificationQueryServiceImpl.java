package com.cargasafe.alert.application.internal.queryservices;

import com.cargasafe.alert.domain.model.entities.Notification;
import com.cargasafe.alert.domain.model.queries.GetNotificationsByAlertIdQuery;
import com.cargasafe.alert.domain.services.NotificationQueryService;
import com.cargasafe.alert.infrastructure.persistence.jpa.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationQueryServiceImpl implements NotificationQueryService {

    private final NotificationRepository notificationRepository;

    public NotificationQueryServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<Notification> handle(GetNotificationsByAlertIdQuery query) {
        return notificationRepository.findByAlertId(query.alertId());
    }
}
