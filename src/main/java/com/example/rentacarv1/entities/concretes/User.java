package com.example.rentacarv1.entities;
import com.example.rentacarv1.entities.abstracts.BaseEntity;
import com.example.rentacarv1.entities.concretes.Customer;
import com.example.rentacarv1.entities.concretes.Employee;
import com.example.rentacarv1.entities.concretes.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@Builder
public class User extends BaseEntity implements UserDetails {

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "surname",nullable = false)
    private String surname;
    @Column(name = "gsm",nullable = false,unique = true)
    private String gsm;
    @Column(name = "email",nullable = false,unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Customer> customers;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Employee> employees;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> authorities;



    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}