package com.cargasafe.alert.domain.model.aggregates;

import com.cargasafe.alert.domain.model.commands.CreateAlertCommand;
import com.cargasafe.alert.domain.model.entities.Incident;
import com.cargasafe.alert.domain.model.entities.Notification;
import com.cargasafe.alert.domain.model.valueobjects.AlertStatus;
import com.cargasafe.alert.domain.model.valueobjects.AlertType;
import com.cargasafe.alert.domain.model.valueobjects.DeliveryOrderId;
import com.cargasafe.alert.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Alert extends AuditableAbstractAggregateRoot<Alert> {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AlertType alertType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AlertStatus alertStatus;

    @OneToMany(mappedBy = "alert", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Incident> incidents = new ArrayList<>();

    @OneToMany(mappedBy = "alert", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Notification> notifications = new ArrayList<>();

    @Embedded
    private DeliveryOrderId deliveryOrderId;

    public Alert(AlertType alertType, AlertStatus alertStatus) {
        this.alertType = alertType;
        this.alertStatus = alertStatus;
    }

    public Alert(CreateAlertCommand command) {
        this.deliveryOrderId = new DeliveryOrderId(command.deliveryOrderId());
        this.alertType = command.alertType();
        this.alertStatus = AlertStatus.OPEN;

        this.incidents.add(new Incident(this, command.description()));
        this.notifications.add(new Notification(this, command.notificationChannel(), command.message(), LocalDateTime.now()));
    }

    public void acknowledge(AlertStatus newStatus) {
        this.alertStatus = newStatus;
        if (newStatus == AlertStatus.ACKNOWLEDGED) {
            for (Incident incident : incidents) {
                incident.setAcknowledgedAt(LocalDateTime.now());
            }
        }
    }

    public void close() {
        this.alertStatus = AlertStatus.CLOSED;
        for (Incident incident : incidents) {
            if (incident.getClosedAt() == null) {
                incident.setClosedAt(LocalDateTime.now());
            }
        }
    }
}
