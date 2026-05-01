package com.cargasafe.alert.interfaces.rest.transformers;

import com.cargasafe.alert.domain.model.aggregates.Alert;
import com.cargasafe.alert.interfaces.rest.resources.AlertResource;

public class AlertResourceFromEntityAssembler {

    public static AlertResource toResourceFromEntity(Alert entity) {
        return new AlertResource(
                entity.getId(),
                entity.getDeliveryOrderId().deliveryOrderId(),
                entity.getAlertType(),
                entity.getAlertStatus(),
                entity.getIncidents().stream()
                        .map(IncidentResourceFromEntityAssembler::toResourceFromEntity)
                        .toList(),
                entity.getNotifications().stream()
                        .map(NotificationResourceFromEntityAssembler::toResourceFromEntity)
                        .toList()
        );
    }
}
