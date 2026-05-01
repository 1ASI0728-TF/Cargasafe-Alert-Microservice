package com.cargasafe.alert.infrastructure.persistence.jpa;

import com.cargasafe.alert.domain.model.entities.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {

    List<Incident> findByAlertId(Long alertId);
}
