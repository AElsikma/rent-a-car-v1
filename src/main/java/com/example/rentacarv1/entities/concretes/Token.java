package com.example.rentacarv1.entities.concretes;

import com.example.rentacarv1.entities.User;
import com.example.rentacarv1.entities.abstracts.BaseEntity;
import com.example.rentacarv1.entities.enums.TokenType;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "tokens")
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Token extends BaseEntity {

    @Column(name = "token", unique = true)
    private String token;

    @Enumerated(EnumType.STRING)
    @Column(name = "token_type")
    private TokenType tokenType = TokenType.BEARER;

    @Column(name = "revoked")
    private boolean revoked;

    @Column(name = "expired")
    private boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}