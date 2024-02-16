package com.example.rentacarv1.services.abstracts;

import com.example.rentacarv1.services.dtos.requests.image.ImageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ImageService {
    public String uploadFile(MultipartFile file, String folderName);
    public ResponseEntity<Map> uploadImage(ImageRequest imageRequest);


}
