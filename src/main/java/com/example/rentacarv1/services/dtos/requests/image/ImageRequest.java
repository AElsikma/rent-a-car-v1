package com.example.rentacarv1.services.dtos.requests.image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageRequest {
    private String name;
    private MultipartFile file;
}
