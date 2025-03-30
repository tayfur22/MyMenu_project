package com.example.Mymenu.project.repository;

import com.example.Mymenu.project.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Restaurant findByName(String name);

    List<Restaurant> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
}
