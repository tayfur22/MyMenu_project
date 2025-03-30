package com.example.Mymenu.project.controller;

import com.example.Mymenu.project.entity.Restaurant;
import com.example.Mymenu.project.service.RestaurantService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }
//
//    @PostMapping("/add")
//    public ResponseEntity<Restaurant> createRestaurant(@Valid @RequestBody Restaurant restaurant) {
//        return ResponseEntity.ok(restaurantService.save(restaurant));
//    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Restaurant> createRestaurant(@Valid @RequestBody Restaurant restaurant, HttpServletRequest request) {
        String contentType = request.getContentType();
        System.out.println("Content-Type: " + contentType);
        return ResponseEntity.ok(restaurantService.save(restaurant));
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long id) {
        return ResponseEntity.ok(restaurantService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@Valid @RequestBody Restaurant restaurant,
                                                       @PathVariable Long id) {
        return ResponseEntity.ok(restaurantService.update(restaurant, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
