package com.notification.controller;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.notification.model.Notification;

@Controller
public class NotificationRealtimeController {

    private final SimpMessagingTemplate messagingTemplate;

    public NotificationRealtimeController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Envia notificação para todos inscritos no tópico "/topic/notifications"
    public void sendNotification(Notification notification) {
        messagingTemplate.convertAndSend("/topic/notifications", notification);
    }
}
