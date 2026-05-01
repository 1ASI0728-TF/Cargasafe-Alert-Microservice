package com.cargasafe.alert.application.internal.commandservices;

import com.cargasafe.alert.application.internal.outboundservices.ExternalTripService;
import com.cargasafe.alert.domain.exceptions.AlertCreationException;
import com.cargasafe.alert.domain.exceptions.AlertNotFoundException;
import com.cargasafe.alert.domain.model.aggregates.Alert;
import com.cargasafe.alert.domain.model.commands.AcknowledgeAlertCommand;
import com.cargasafe.alert.domain.model.commands.CloseAlertCommand;
import com.cargasafe.alert.domain.model.commands.CreateAlertCommand;
import com.cargasafe.alert.domain.model.entities.Notification;
import com.cargasafe.alert.domain.model.valueobjects.AlertStatus;
import com.cargasafe.alert.domain.model.valueobjects.NotificationChannel;
import com.cargasafe.alert.domain.services.AlertCommandService;
import com.cargasafe.alert.infrastructure.persistence.jpa.AlertRepository;
import com.cargasafe.alert.infrastructure.persistence.jpa.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AlertCommandServiceImpl implements AlertCommandService {

    private final AlertRepository alertRepository;
    private final NotificationRepository notificationRepository;
    private final ExternalTripService externalTripService;

    public AlertCommandServiceImpl(AlertRepository alertRepository,
                                   NotificationRepository notificationRepository,
                                   ExternalTripService externalTripService) {
        this.alertRepository = alertRepository;
        this.notificationRepository = notificationRepository;
        this.externalTripService = externalTripService;
    }

    @Override
    public Optional<Alert> handle(CreateAlertCommand command) {
        if (!externalTripService.deliveryOrderExists(command.deliveryOrderId())) {
            throw new AlertCreationException(
                    "DeliveryOrder with id " + command.deliveryOrderId() + " does not exist.");
        }
        try {
            var alert = new Alert(command);
            var savedAlert = alertRepository.save(alert);
            return Optional.of(savedAlert);
        } catch (Exception e) {
            throw new AlertCreationException("Failed to create alert: " + e.getMessage());
        }
    }

    @Override
    public Optional<Alert> handle(AcknowledgeAlertCommand command) {
        var alert = alertRepository.findById(command.alertId())
                .orElseThrow(() -> new AlertNotFoundException(command.alertId()));
        alert.acknowledge(AlertStatus.ACKNOWLEDGED);
        var updatedAlert = alertRepository.save(alert);
        return Optional.of(updatedAlert);
    }

    @Override
    public Optional<Alert> handle(CloseAlertCommand command) {
        var alert = alertRepository.findById(command.alertId())
                .orElseThrow(() -> new AlertNotFoundException(command.alertId()));

        if (alert.getAlertStatus() == AlertStatus.OPEN) {
            throw new IllegalStateException("Cannot close an alert that has not been acknowledged first.");
        }
        if (alert.getAlertStatus() == AlertStatus.CLOSED) {
            throw new IllegalStateException("This alert is already closed.");
        }

        alert.close();

        var notification = new Notification(
                alert,
                NotificationChannel.EMAIL,
                "Alert " + alert.getId() + " has been closed.",
                LocalDateTime.now()
        );
        alert.getNotifications().add(notification);
        notificationRepository.save(notification);

        var updatedAlert = alertRepository.save(alert);
        return Optional.of(updatedAlert);
    }
}
