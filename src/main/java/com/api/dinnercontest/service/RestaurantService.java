package com.api.dinnercontest.service;

import com.api.dinnercontest.model.RestaurantModel;
import com.api.dinnercontest.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    public void saveRestaurant(RestaurantModel restaurantModel) {
        restaurantRepository.saveRestaurant(restaurantModel);
    }

    public RestaurantModel getRestaurant(Long id) {
        return restaurantRepository.getRestaurant(id);
    }

    public boolean checkUserRestaurant(Long restaurant, Long user) {
        return restaurantRepository.checkUserRestaurant(restaurant, user);
    }

    public void deleteRestaurant(Long restaurant) {
        restaurantRepository.deleteRestaurant(restaurant);
    }
}
