package com.example.petShelter.controller;

import com.example.petShelter.model.AnimalAvatar;
import com.example.petShelter.service.AnimalAvatarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

/**
 * The class consists of codes in order to upload and
 download the photos of animals for REST API

 *
 * @author Khilola Kushbakova
 */

@Tag(name="animalAvatarController")
@RestController
@RequestMapping("avatar")
public class AnimalAvatarController {
    private final AnimalAvatarService animalAvatarService;

    public AnimalAvatarController(AnimalAvatarService animalAvatarService) {
        this.animalAvatarService = animalAvatarService;
    }

    @Operation(summary = "Upload an image for a specific animal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Image uploaded successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request, file size is too big")
    })
    @PostMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadImage(@PathVariable Long id, @RequestParam MultipartFile image) throws IOException {
        if (image.getSize() >= 1024 * 300) {
            return ResponseEntity.badRequest().body("The size of your file is big to upload");
        }
        animalAvatarService.uploadImage(id, image);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Download Animal Image")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Image data retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Animal avatar not found")
    })

    @GetMapping(value = "/{id}/image/preview")
    public ResponseEntity<byte[]> downloadImage(@PathVariable Long id) {
        AnimalAvatar animalAvatar = animalAvatarService.findAnimalAvatarById(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(animalAvatar.getMediaType()));
        headers.setContentLength(animalAvatar.getData().length);


        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(animalAvatar.getData());
    }

    @Operation(summary = "Download animal avatar image from file by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved animal avatar image"),
            @ApiResponse(responseCode = "404", description = "Animal avatar not found")
    })
    @GetMapping(value = "/{id}/image-from-file")
    public void downloadAvatar(@PathVariable Long id, HttpServletResponse response) throws IOException {
        AnimalAvatar animalAvatar = animalAvatarService.findAnimalAvatarById(id);

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
