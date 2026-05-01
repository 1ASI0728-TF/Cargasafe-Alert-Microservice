package com.cargasafe.alert.interfaces.rest.transformers;

import com.cargasafe.alert.domain.model.entities.Incident;
import com.cargasafe.alert.interfaces.rest.resources.IncidentResource;

public class IncidentResourceFromEntityAssembler {

    public static IncidentResource toResourceFromEntity(Incident incident) {
        return new IncidentResource(
                incident.getId(),
                incident.getAlert().getId(),
                incident.getDescription(),
                incident.getCreatedAt(),
                incident.getAcknowledgedAt(),
                incident.getClosedAt()
        );
    }
}
