package com.example.rentacarv1.repositories;

import com.example.rentacarv1.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
