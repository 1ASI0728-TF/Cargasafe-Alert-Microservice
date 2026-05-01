package com.cargasafe.alert.interfaces.rest;

import com.cargasafe.alert.domain.model.queries.GetAlertByIdQuery;
import com.cargasafe.alert.domain.model.queries.GetAlertsByStatusQuery;
import com.cargasafe.alert.domain.model.queries.GetAlertsByTypeQuery;
import com.cargasafe.alert.domain.model.queries.GetAllAlertsQuery;
import com.cargasafe.alert.domain.model.valueobjects.AlertStatus;
import com.cargasafe.alert.domain.model.valueobjects.AlertType;
import com.cargasafe.alert.domain.services.AlertCommandService;
import com.cargasafe.alert.domain.services.AlertQueryService;
import com.cargasafe.alert.interfaces.rest.resources.AlertResource;
import com.cargasafe.alert.interfaces.rest.resources.CreateAlertResource;
import com.cargasafe.alert.interfaces.rest.transformers.AcknowledgeAlertCommandFromResourceAssembler;
import com.cargasafe.alert.interfaces.rest.transformers.AlertResourceFromEntityAssembler;
import com.cargasafe.alert.interfaces.rest.transformers.CloseAlertCommandFromResourceAssembler;
import com.cargasafe.alert.interfaces.rest.transformers.CreateAlertCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/alerts")
@Tag(name = "Alerts", description = "Endpoint for managing alerts")
public class AlertController {

    private final AlertCommandService alertCommandService;
    private final AlertQueryService alertQueryService;

    public AlertController(AlertCommandService alertCommandService, AlertQueryService alertQueryService) {
        this.alertCommandService = alertCommandService;
        this.alertQueryService = alertQueryService;
    }

    @PostMapping
    public ResponseEntity<AlertResource> createAlert(@RequestBody CreateAlertResource resource) {
        var command = CreateAlertCommandFromResourceAssembler.toCommandFromResource(resource);
        var alert = alertCommandService.handle(command);
        if (alert.isEmpty()) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(AlertResourceFromEntityAssembler.toResourceFromEntity(alert.get()));
    }

    @PatchMapping("/{alertId}/acknowledgment")
    public ResponseEntity<AlertResource> acknowledgeAlert(@PathVariable Long alertId) {
        var command = AcknowledgeAlertCommandFromResourceAssembler.toCommandFromResource(alertId);
        var updatedAlert = alertCommandService.handle(command);
        if (updatedAlert.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(AlertResourceFromEntityAssembler.toResourceFromEntity(updatedAlert.get()));
    }

    @PatchMapping("/{alertId}/closure")
    public ResponseEntity<AlertResource> closeAlert(@PathVariable Long alertId) {
        var command = CloseAlertCommandFromResourceAssembler.toCommandFromResource(alertId);
        var updatedAlert = alertCommandService.handle(command);
        if (updatedAlert.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(AlertResourceFromEntityAssembler.toResourceFromEntity(updatedAlert.get()));
    }

    @GetMapping
    public ResponseEntity<List<AlertResource>> getAllAlerts() {
        var alerts = alertQueryService.handle(new GetAllAlertsQuery());
        var resources = alerts.stream()
                .map(AlertResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{alertId}")
    public ResponseEntity<AlertResource> getAlertById(@PathVariable Long alertId) {
        var alert = alertQueryService.handle(new GetAlertByIdQuery(alertId));
        if (alert.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(AlertResourceFromEntityAssembler.toResourceFromEntity(alert.get()));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<AlertResource>> getAlertsByType(@PathVariable String type) {
        var alertType = AlertType.valueOf(type.toUpperCase());
        var alerts = alertQueryService.handle(new GetAlertsByTypeQuery(alertType));
        var resources = alerts.stream()
                .map(AlertResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<AlertResource>> getAlertsByStatus(@PathVariable String status) {
        var alertStatus = AlertStatus.valueOf(status.toUpperCase());
        var alerts = alertQueryService.handle(new GetAlertsByStatusQuery(alertStatus));
        var resources = alerts.stream()
                .map(AlertResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
}
