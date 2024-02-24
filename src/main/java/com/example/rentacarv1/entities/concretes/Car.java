package com.example.rentacarv1.entities.concretes;
import com.example.rentacarv1.entities.abstracts.BaseEntity;
import com.example.rentacarv1.entities.enums.CarState;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car extends BaseEntity {


    @Column (name = "kilometer", nullable = false)
    private int kilometer;

    @Column(name = "year", nullable = false)
    private int year;

    @Column(name = "daily_price", nullable = false)
    private double dailyPrice;

    @Column(name = "plate",unique = true)
    private String plate;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "car_state")
    @Enumerated(EnumType.STRING)
    private CarState carState=CarState.AVAILABLE;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @OneToMany(mappedBy = "car")
    @JsonIgnore
    private List<Rental> rentals;
}