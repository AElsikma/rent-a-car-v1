package com.example.rentacarv1.services.abstracts;

import com.example.rentacarv1.repositories.LogRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;


public interface LogService {

   void log(String message);



}
