package com.example.Mymenu.project.controller;

import com.example.Mymenu.project.entity.Rating;
import com.example.Mymenu.project.service.RatingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
//@RequestMapping(value = "/ratings", produces = "application/json")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping
    public ResponseEntity<Rating> createRating(@Valid @RequestBody Rating rating) {
        return ResponseEntity.ok(ratingService.save(rating));
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings() {
        return ResponseEntity.ok(ratingService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rating> getRatingById(@PathVariable Long id) {
        return ResponseEntity.ok(ratingService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rating> updateRating(@Valid @RequestBody Rating rating, @PathVariable Long id) {
        return ResponseEntity.ok(ratingService.update(rating, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable Long id) {
        ratingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(ratingService.findByUserId(userId));
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Rating>> getRatingsByRestaurantId(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(ratingService.findByRestaurantId(restaurantId));
    }
}
