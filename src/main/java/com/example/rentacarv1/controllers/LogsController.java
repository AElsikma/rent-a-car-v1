package com.example.rentacarv1.controllers;



import com.example.rentacarv1.entities.concretes.Log;
import com.example.rentacarv1.services.abstracts.LogService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/api/logs")
@AllArgsConstructor

public class LogsController {
    private final LogService logService;
    private static final Logger log = LoggerFactory.getLogger(Log.class);

    @GetMapping("/getAll")
    public void logMessage(){
        log.info("Log Info Logging is enabled");
        log.debug("Log Debug Logging is enabled");
        log.warn("Log Warn Logging is enabled");
    }
    @GetMapping("/example")
    public String exampleEndpoint() {
        // Loglama yapma
        logService.log("Bu bir örnek log mesajıdır.");

        // Geri dönüş yapma
        return "İşlem tamamlandı!";
    }





}