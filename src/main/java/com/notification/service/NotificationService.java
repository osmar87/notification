package com.notification.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.notification.controller.NotificationRealtimeController;
import com.notification.model.Notification;
import com.notification.repositoy.NotificationRepository;

@Service
public class NotificationService {

    private final NotificationRepository repository;
    private final NotificationRealtimeController realtimeController;

    // 🔧 Injeção de dependência via construtor (recomendada)
    public NotificationService(NotificationRepository repository, NotificationRealtimeController realtimeController) {
        this.repository = repository;
        this.realtimeController = realtimeController;
    }


     public Notification createNotification(String userId, String msg) {
        Notification notification = new Notification(userId, msg);
        Notification saved = repository.save(notification);

        // envia em tempo real
        realtimeController.sendNotification(saved);

        return saved;
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
