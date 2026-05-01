package com.cargasafe.alert.infrastructure.persistence.jpa;

import com.cargasafe.alert.domain.model.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByAlertId(Long alertId);
}
