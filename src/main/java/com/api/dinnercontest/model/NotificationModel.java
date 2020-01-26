package com.api.dinnercontest.model;

public class NotificationModel {

    private Long notificationId;

    private Long ownerGroup;

    private String message;

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public Long getOwnerGroup() {
        return ownerGroup;
    }

    public void setOwnerGroup(Long ownerGroup) {
        this.ownerGroup = ownerGroup;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NotificationModel() {
    }
}
