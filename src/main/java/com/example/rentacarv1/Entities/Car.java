package com.example.rentacarv1.Entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name = "kilometer", nullable = false)
    private int kilometer;

    @Column(name = "year", nullable = false)
    private int year;

    @Column(name = "daily_price", nullable = false)
    private Double dailyPrice;

    @Column(name = "plate",unique = true)
    private String plate;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model models;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color colors;
}