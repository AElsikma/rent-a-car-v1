package com.example.rentacarv1.controllers;


import com.example.rentacarv1.services.abstracts.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/logs")
public class LogsController {

    @Autowired
    private LogService logService;

    @GetMapping("/file")
    public String logToFile() {
        logService.logToFile("Dosyaya loglama örneği", "INFO");
        return "Dosyaya loglama başarılı!";
    }

    @GetMapping("/database")
    public String logToDatabase() {
        logService.logToDatabase("Veritabanına loglama örneği", "INFO");
        return "Veritabanına loglama başarılı!";
    }
}
