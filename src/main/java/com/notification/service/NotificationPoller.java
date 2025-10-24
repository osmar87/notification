package com.notification.service;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.notification.model.Notification;

@Service
public class NotificationPoller {

    private final NotificationService notificationService;
    private final RestTemplate restTemplate = new RestTemplate();

    public NotificationPoller(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Scheduled(fixedRate = 5000) // a cada 5 segundos
    public void checkExternalApi() {
        // Chamar API externa
        ResponseEntity<Notification[]> response =
            restTemplate.getForEntity("https://api-externa.com/notifications", Notification[].class);

        Notification[] notifications = response.getBody();
        if (notifications != null) {
            for (Notification n : notifications) {
                // Salva e envia via WebSocket
                notificationService.createNotification(n.getUserId(), n.getMsg());
            }
        }
    }
}
