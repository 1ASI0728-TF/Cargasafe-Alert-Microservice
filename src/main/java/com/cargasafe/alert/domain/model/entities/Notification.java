package com.cargasafe.alert.domain.model.entities;

import com.cargasafe.alert.domain.model.aggregates.Alert;
import com.cargasafe.alert.domain.model.commands.SendNotificationCommand;
import com.cargasafe.alert.domain.model.valueobjects.NotificationChannel;
import com.cargasafe.alert.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Notification extends AuditableModel {

    @ManyToOne
    @NotNull
    @JoinColumn(name = "alert_id", referencedColumnName = "id", nullable = false)
    private Alert alert;

    @Enumerated(EnumType.STRING)
    private NotificationChannel notificationChannel;

    private String message;
    private LocalDateTime sentAt;

    public Notification(Alert alert, NotificationChannel notificationChannel, String message, LocalDateTime sentAt) {
        this.alert = alert;
        this.notificationChannel = notificationChannel;
        this.message = message;
        this.sentAt = sentAt;
    }

    public Notification(SendNotificationCommand command, Alert alert) {
        this.alert = alert;
        this.notificationChannel = command.notificationChannel();
        this.message = command.message();
        this.sentAt = LocalDateTime.now();
    }
}
