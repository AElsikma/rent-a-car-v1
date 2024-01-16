package com.example.rentacarv1.entities.concretes;
import com.example.rentacarv1.entities.abstracts.BaseEntity;
import com.example.rentacarv1.entities.concretes.Car;
import com.example.rentacarv1.entities.concretes.Customer;
import com.example.rentacarv1.entities.concretes.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rentals")
public class Rental extends BaseEntity {

    @Column(name = "start_date",nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Column(name = "start_kilometer",nullable = false)
    private int startKilometer;

    @Column(name = "end_kilometer")
    private Long endKilometer;

    @Column(name = "total_price",nullable = false)
    private Double totalPrice;

    @Column(name = "discount")
    private Double discount;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
}
