package com.example.rentacarv1.services.concretes;

import com.example.rentacarv1.entities.concretes.Log;
import com.example.rentacarv1.repositories.LogRepository;
import com.example.rentacarv1.services.abstracts.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class LogManager implements LogService {

    private static final Logger fileLogger = LoggerFactory.getLogger("fileLogger");
    private static final Logger dbLogger = LoggerFactory.getLogger("dbLogger");

    @Autowired
    private LogRepository logRepository;

    @Override
    public void logToFile(String message, String logLevel) {
        fileLogger.info(message);
        saveToDatabase(message, logLevel);
    }

    @Override
    public void logToDatabase(String message, String logLevel) {
        dbLogger.info(message);
        saveToDatabase(message, logLevel);
    }

    @Override
    public void saveToDatabase(String message, String logLevel) {
        Log log = new Log(message, logLevel);
        logRepository.save(log);
    }
}
