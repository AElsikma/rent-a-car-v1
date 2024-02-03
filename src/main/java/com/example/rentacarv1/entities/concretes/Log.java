package com.example.rentacarv1.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "logs")
public class Log  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    private String logLevel;
    private LocalDateTime creationTime;



    public Log() {
        this.creationTime = LocalDateTime.now();
    }

    public Log(String message, String logLevel) {
        this.message = message;
        this.logLevel = logLevel;
        this.creationTime = LocalDateTime.now();
    }
}
