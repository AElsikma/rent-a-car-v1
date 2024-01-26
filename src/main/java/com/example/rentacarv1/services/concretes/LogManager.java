package com.example.rentacarv1.services.concretes;

import com.example.rentacarv1.entities.concretes.Log;
import com.example.rentacarv1.repositories.LogRepository;
import com.example.rentacarv1.services.abstracts.LogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class LogManager implements LogService {

    private  final LogRepository logRepository;
    @Override
    public void log(String message) {
        Log log =new Log();
        log.setLogMessage(log.getLogMessage());
        log.setLogLevel(log.getLogLevel());
        log.setCreatedAt(log.getCreatedAt());
        logRepository.save(log);

    }
}
