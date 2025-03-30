package com.example.Mymenu.project.service;

import com.example.Mymenu.project.entity.Rating;

import java.util.List;

public interface RatingService extends BaseService<Rating,Long>{

    List<Rating> findByUserId(Long userId);

    List<Rating> findByRestaurantId(Long restaurantId);
}
