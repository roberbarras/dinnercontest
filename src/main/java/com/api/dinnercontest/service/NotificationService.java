package com.api.dinnercontest.service;

import com.api.dinnercontest.model.NotificationModel;
import com.api.dinnercontest.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public NotificationService() {
    }

    public void saveNotification(NotificationModel notificationModel) {


    }
}
