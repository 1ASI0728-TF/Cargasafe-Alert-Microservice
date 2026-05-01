package com.cargasafe.alert.infrastructure.persistence.jpa;

import com.cargasafe.alert.domain.model.aggregates.Alert;
import com.cargasafe.alert.domain.model.valueobjects.AlertStatus;
import com.cargasafe.alert.domain.model.valueobjects.AlertType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {

    List<Alert> findByAlertType(AlertType alertType);

    List<Alert> findByAlertStatus(AlertStatus alertStatus);
}
