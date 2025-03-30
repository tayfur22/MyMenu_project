package com.example.Mymenu.project.service.impl;

import com.example.Mymenu.project.entity.Rating;
import com.example.Mymenu.project.entity.Restaurant;
import com.example.Mymenu.project.entity.User;
import com.example.Mymenu.project.exception.EntityNotFoundException;
import com.example.Mymenu.project.exception.ResourceNotFoundException;
import com.example.Mymenu.project.repository.RatingRepository;
import com.example.Mymenu.project.repository.RestaurantRepository;
import com.example.Mymenu.project.repository.UserRepository;
import com.example.Mymenu.project.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final   RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository,
                             RestaurantRepository restaurantRepository,
                             UserRepository userRepository) {
        this.ratingRepository = ratingRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating findById(Long id) {
        return ratingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rating not found with ID: " + id));
    }

//    @Override
//    public Rating save(Rating rating) {
//        return ratingRepository.save(rating);
//    }

    @Override
    public Rating save(Rating rating) {
        if (rating.getRestaurant() == null || rating.getRestaurant().getId() == null) {
            throw new ResourceNotFoundException("Restaurant ID must not be null");
        }

        if (rating.getUser() == null || rating.getUser().getId() == null) {
            throw new ResourceNotFoundException("User ID must not be null");
        }

        // Mövcud restoranı tap
        Restaurant restaurant = restaurantRepository.findById(rating.getRestaurant().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id: " + rating.getRestaurant().getId()));

        // Mövcud istifadəçini tap
        User user = userRepository.findById(rating.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + rating.getUser().getId()));

        // Obyektləri rating-ə əlavə et
        rating.setRestaurant(restaurant);
        rating.setUser(user);

        return ratingRepository.save(rating);
    }


    @Override
    public Rating update(Rating rating, Long id) {
        if (!ratingRepository.existsById(id)){
            throw new EntityNotFoundException("Rating not found with id" + id);
        }
        rating.setId(id);
        return save(rating);
    }

    @Override
    public void deleteById(Long id) {
        if (!ratingRepository.existsById(id)){
            throw new ResourceNotFoundException("Rating not found with id" + id);
        }
        ratingRepository.deleteById(id);
    }

    @Override
    public List<Rating> findByUserId(Long userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> findByRestaurantId(Long restaurantId) {
        return ratingRepository.findByRestaurantId(restaurantId);
    }
}
