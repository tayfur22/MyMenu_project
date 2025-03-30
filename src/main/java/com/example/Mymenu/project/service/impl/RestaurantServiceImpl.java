package com.example.Mymenu.project.service.impl;

import com.example.Mymenu.project.entity.Restaurant;
import com.example.Mymenu.project.exception.EntityNotFoundException;
import com.example.Mymenu.project.exception.ResourceNotFoundException;
import com.example.Mymenu.project.repository.RestaurantRepository;
import com.example.Mymenu.project.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant findById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with ID: " + id));
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteById(Long id) {
        if (!restaurantRepository.existsById(id)) {
            throw new ResourceNotFoundException("Restaurant not found with ID: " + id);
        }
        restaurantRepository.deleteById(id);
    }

    @Override
    public Restaurant update(Restaurant restaurant, Long id) {
        if (!restaurantRepository.existsById(id)) {
            throw new EntityNotFoundException("Restaurant not found with id" + id);
        }
        restaurant.setId(id);
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant findByName(String name) {
        return Optional.ofNullable(restaurantRepository.findByName(name))
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with name: " + name));
    }

    @Override
    public List<Restaurant> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return restaurantRepository.findByPriceBetween(minPrice, maxPrice);
    }
}
