package com.cargasafe.alert.interfaces.rest.resources;

import java.time.LocalDateTime;

public record IncidentResource(
        Long id,
        Long alertId,
        String description,
        LocalDateTime createdAt,
        LocalDateTime acknowledgeAt,
        LocalDateTime closedAt
) {
}
