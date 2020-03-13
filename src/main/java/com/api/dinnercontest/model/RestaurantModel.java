package com.api.dinnercontest.model;

import java.time.LocalDateTime;

public class RestaurantModel extends UserIdTokenModel {

    private long restaurantId;

    private String name;

    private long host;

    private long group;

    private LocalDateTime date;

    private String address;

    private String photo;

    private boolean visible;

    private LocalDateTime creationDate;

    private LocalDateTime visibleDate;

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getHost() {
        return host;
    }

    public void setHost(long host) {
        this.host = host;
    }

    public long getGroup() {
        return group;
    }

    public void setGroup(long group) {
        this.group = group;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getVisibleDate() {
        return visibleDate;
    }

    public void setVisibleDate(LocalDateTime visibleDate) {
        this.visibleDate = visibleDate;
    }

    public RestaurantModel() {
    }

    @Override
    public String toString() {
        return "RestaurantModel{" +
                "restaurantId=" + restaurantId +
                ", name='" + name + '\'' +
                ", host=" + host +
                ", group=" + group +
                ", date=" + date +
                ", address='" + address + '\'' +
                ", photo='" + photo + '\'' +
                ", visible=" + visible +
                ", creationDate=" + creationDate +
                ", visibleDate=" + visibleDate +
                '}';
    }
}
