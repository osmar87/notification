package com.notification.service;

import com.notification.model.Notification;
import com.notification.repositoy.NotificationRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NotificationService {

    private final NotificationRepository repository;

    // 🔧 Injeção de dependência via construtor (recomendada)
    public NotificationService(NotificationRepository repository) {
        this.repository = repository;
    }

    // 📨 Criar uma nova notificação
    public Notification createNotification(String userId, String msg) {
        Notification notification = new Notification(userId, msg);
        return repository.save(notification);
    }

    // 🔍 Listar todas as notificações de um usuário
    public List<Notification> getUserNotifications(String userId) {
        return repository.findByUserId(userId);
    }

    // 🔍 Buscar notificações não vistas
    public List<Notification> getUnseenNotifications(String userId) {
        return repository.findByUserIdAndIsVistoFalse(userId);
    }

    // 👁️ Marcar notificação como vista
    public Notification markAsSeen(UUID id) {
        Notification notification = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificação não encontrada"));
        notification.setVisto(true);
        return repository.save(notification);
    }

    // ❌ Deletar notificação
    public void deleteNotification(UUID id) {
        repository.deleteById(id);
    }
}
