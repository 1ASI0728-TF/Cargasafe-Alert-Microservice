package com.cargasafe.alert.application.internal.outboundservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.Map;

@Service
public class ExternalTripService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalTripService.class);

    private final RestClient restClient;

    public ExternalTripService(@Value("${integrations.trip-service.base-url}") String baseUrl) {
        this.restClient = RestClient.builder().baseUrl(baseUrl).build();
    }

    public boolean deliveryOrderExists(Long deliveryOrderId) {
        try {
            Map<String, Boolean> result = restClient.get()
                    .uri("/api/v1/delivery-orders/{id}/exists", deliveryOrderId)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {});
            return result != null && Boolean.TRUE.equals(result.get("exists"));
        } catch (RestClientException e) {
            LOGGER.error("Failed to check delivery order existence for id={}: {}", deliveryOrderId, e.getMessage());
            return false;
        }
    }
}
