package com.example.rentacarv1.controllers;


import com.example.rentacarv1.services.abstracts.ImageService;
import com.example.rentacarv1.services.dtos.requests.image.ImageRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/images")
@AllArgsConstructor
@CrossOrigin
public class ImageController {
    private ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<Map> upload(ImageRequest imageRequest) {
        try {
            return imageService.uploadImage(imageRequest);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}
