package com.example.petShelter.controller;

import com.example.petShelter.model.AnimalAvatar;
import com.example.petShelter.service.AnimalAvatarService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("avatar")
public class AnimalAvatarController {
    private final AnimalAvatarService animalAvatarService;

    public AnimalAvatarController(AnimalAvatarService animalAvatarService) {
        this.animalAvatarService = animalAvatarService;
    }

    @PostMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadImage(@PathVariable Long id, @RequestParam MultipartFile image) throws IOException {
        if (image.getSize() >= 1024 * 300) {
            return ResponseEntity.badRequest().body("The size of your file is big to upload");
        }
        animalAvatarService.uploadImage(id, image);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}/image/preview")
    public ResponseEntity<byte[]> downloadImage(@PathVariable Long animalId) {
        AnimalAvatar animalAvatar = animalAvatarService.findAnimalAvatarById(animalId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(animalAvatar.getMediaType()));
        headers.setContentLength(animalAvatar.getData().length);


        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(animalAvatar.getData());

    }

    @GetMapping(value = "/{id}/image-from-file")
    public void downloadAvatar(@PathVariable Long animalId, HttpServletResponse response) throws IOException {
        AnimalAvatar animalAvatar = animalAvatarService.findAnimalAvatarById(animalId);

        Path path = Path.of(animalAvatar.getFilePath());

        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream();) {
            response.setStatus(200);
            response.setContentType(animalAvatar.getMediaType());
            response.setContentLength((int) animalAvatar.getFileSize());
            is.transferTo(os);
        }
    }
}
