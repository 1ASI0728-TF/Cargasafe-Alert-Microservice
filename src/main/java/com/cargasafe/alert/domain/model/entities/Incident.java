package com.cargasafe.alert.domain.model.entities;

import com.cargasafe.alert.domain.model.aggregates.Alert;
import com.cargasafe.alert.domain.model.commands.CreateIncidentFromAlertCommand;
import com.cargasafe.alert.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Incident extends AuditableModel {

    @ManyToOne
    @NotNull
    @JoinColumn(name = "alert_id", referencedColumnName = "id", nullable = false)
    private Alert alert;

    private String description;
    private LocalDateTime acknowledgedAt;
    private LocalDateTime closedAt;

    public Incident(Alert alert, String description) {
        this.alert = alert;
        this.description = description;
    }

    public Incident(CreateIncidentFromAlertCommand command, Alert alert) {
        this.alert = alert;
        this.description = command.description();
    }
}
