package com.cargasafe.alert.interfaces.rest;

import com.cargasafe.alert.domain.model.queries.GetNotificationsByAlertIdQuery;
import com.cargasafe.alert.domain.services.NotificationQueryService;
import com.cargasafe.alert.interfaces.rest.resources.NotificationResource;
import com.cargasafe.alert.interfaces.rest.transformers.NotificationResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
@Tag(name = "Notifications", description = "Endpoint for managing notifications")
public class NotificationController {

    private final NotificationQueryService notificationQueryService;

    public NotificationController(NotificationQueryService notificationQueryService) {
        this.notificationQueryService = notificationQueryService;
    }

    @GetMapping("/alert/{alertId}")
    public ResponseEntity<List<NotificationResource>> getNotificationsByAlertId(@PathVariable Long alertId) {
        var notifications = notificationQueryService.handle(new GetNotificationsByAlertIdQuery(alertId));
        var resources = notifications.stream()
                .map(NotificationResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
}
