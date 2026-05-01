package com.cargasafe.alert.domain.model.queries;

import com.cargasafe.alert.domain.model.valueobjects.AlertStatus;

public record GetAlertsByStatusQuery(AlertStatus status) {
}
