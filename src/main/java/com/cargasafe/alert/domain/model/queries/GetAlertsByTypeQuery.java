package com.cargasafe.alert.domain.model.queries;

import com.cargasafe.alert.domain.model.valueobjects.AlertType;

public record GetAlertsByTypeQuery(AlertType type) {
}
