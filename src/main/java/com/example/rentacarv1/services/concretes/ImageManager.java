package com.example.rentacarv1.services.concretes;

import com.cloudinary.Cloudinary;
import com.example.rentacarv1.core.utilities.mappers.ModelMapperService;
import com.example.rentacarv1.entities.concretes.Image;
import com.example.rentacarv1.repositories.ImageRepository;
import com.example.rentacarv1.services.abstracts.ImageService;
import com.example.rentacarv1.services.dtos.requests.image.ImageRequest;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ImageManager implements ImageService {

    private ImageRepository imageRepository;
    @Resource
    private Cloudinary cloudinary;
    @Override
    public String uploadFile(MultipartFile file, String folderName) {
        try{
            HashMap<Object, Object> options = new HashMap<>();
            options.put("folder", folderName);
            Map uploadedFile = cloudinary.uploader().upload(file.getBytes(), options);
            String publicId = (String) uploadedFile.get("public_id");
            return cloudinary.url().secure(true).generate(publicId);

        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResponseEntity<Map> uploadImage(ImageRequest imageRequest) {
        try {
            if (imageRequest.getName().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (imageRequest.getFile().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            Image image = new Image();
            image.setName(imageRequest.getName());
            image.setUrl(uploadFile(imageRequest.getFile(), "folder_1"));
            if(image.getUrl() == null) {
                return ResponseEntity.badRequest().build();
            }
            imageRepository.save(image);
            return ResponseEntity.ok().body(Map.of("url", image.getUrl()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
