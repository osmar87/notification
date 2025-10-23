package com.notification.repositoy;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.notification.model.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, UUID> {

    // 🔍 Exemplo: buscar todas as notificações de um usuário
    java.util.List<Notification> findByUserId(String userId);

    // 🔍 Exemplo: buscar notificações não vistas
    java.util.List<Notification> findByUserIdAndIsVistoFalse(String userId);
}
