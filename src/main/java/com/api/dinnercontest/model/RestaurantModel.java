package com.api.dinnercontest.model;

import java.time.LocalDateTime;

public class RestaurantModel {

    private long restaurantId;

    private String name;

    private long host;

    private LocalDateTime date;

    private String address;

    private String photo;

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

    public RestaurantModel() {
    }

    @Override
    public String toString() {
        return "RestaurantModel{" +
                "restaurantId=" + restaurantId +
                ", name='" + name + '\'' +
                ", host=" + host +
                ", date=" + date +
                ", address='" + address + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
