package com.example.Mymenu.project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating cannot be more than 5")
    @NotNull
    private int rate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
//    @JsonBackReference
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
//    @JsonBackReference
    @JsonIgnore
    private Restaurant restaurant;

}
