package com.example.Mymenu.project.service;

import com.example.Mymenu.project.entity.Restaurant;

import java.math.BigDecimal;
import java.util.List;

public interface RestaurantService extends BaseService<Restaurant,Long>{

    Restaurant findByName(String name);

    List<Restaurant> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
}
