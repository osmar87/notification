package com.notification.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notification.model.Notification;
import com.notification.service.NotificationService;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    // 📨 Criar uma nova notificação
    @PostMapping
    public ResponseEntity<Notification> create(@RequestBody Notification notification) {
        Notification created = service.createNotification(notification.getUserId(), notification.getMsg());
        return ResponseEntity.ok(created);
    }

    // 🔍 Buscar todas as notificações de um usuário
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notification>> getUserNotifications(@PathVariable String userId) {
        List<Notification> notifications = service.getUserNotifications(userId);
        return ResponseEntity.ok(notifications);
    }

    // 🔍 Buscar notificações não vistas
    @GetMapping("/user/{userId}/unseen")
    public ResponseEntity<List<Notification>> getUnseenNotifications(@PathVariable String userId) {
        List<Notification> unseen = service.getUnseenNotifications(userId);
        return ResponseEntity.ok(unseen);
    }

    // 👁️ Marcar notificação como vista
    @PutMapping("/{id}/seen")
    public ResponseEntity<Notification> markAsSeen(@PathVariable UUID id) {
        Notification updated = service.markAsSeen(id);
        return ResponseEntity.ok(updated);
    }

    // ❌ Deletar uma notificação
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }
}
