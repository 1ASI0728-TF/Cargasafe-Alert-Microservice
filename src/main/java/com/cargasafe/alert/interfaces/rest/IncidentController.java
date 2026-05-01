package com.cargasafe.alert.interfaces.rest;

import com.cargasafe.alert.domain.model.queries.GetIncidentsByAlertIdQuery;
import com.cargasafe.alert.domain.services.IncidentQueryService;
import com.cargasafe.alert.interfaces.rest.resources.IncidentResource;
import com.cargasafe.alert.interfaces.rest.transformers.IncidentResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/incidents")
@Tag(name = "Incidents", description = "Endpoint for managing incidents")
public class IncidentController {

    private final IncidentQueryService incidentQueryService;

    public IncidentController(IncidentQueryService incidentQueryService) {
        this.incidentQueryService = incidentQueryService;
    }

    @GetMapping("/alert/{alertId}")
    public ResponseEntity<List<IncidentResource>> getIncidentsByAlertId(@PathVariable Long alertId) {
        var incidents = incidentQueryService.handle(new GetIncidentsByAlertIdQuery(alertId));
        var resources = incidents.stream()
                .map(IncidentResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
}
