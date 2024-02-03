package com.example.rentacarv1.services.abstracts;

public interface LogService {
    void logToFile(String message, String logLevel);
    void logToDatabase(String message, String logLevel);
    void saveToDatabase(String message, String logLevel);
}
