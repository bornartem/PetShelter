package com.example.petShelter.service;

import com.example.petShelter.model.AnimalAvatar;
import com.example.petShelter.model.Animals;
import com.example.petShelter.repository.AnimalAvatarRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;
/**
 * The class consists of logic of the project, which has the method of download and upload the photos of animals
 * @author Khilola Kushbakova

 */
@Service
@Transactional
@Slf4j
public class AnimalAvatarService {

    @Value("${path.to.avatars.folder}")
    private String avatarsDir;

    private Logger log = LoggerFactory.getLogger(AnimalAvatarService.class);

    private final AnimalsService animalsService;
    private final AnimalAvatarRepository animalAvatarRepository;

    public AnimalAvatarService(AnimalsService animalsService, AnimalAvatarRepository animalAvatarRepository) {
        this.animalsService = animalsService;
        this.animalAvatarRepository = animalAvatarRepository;
    }


    /**
     * Uploads an image for a specific animal.
     *
     * @param animalId The ID of the animal
     * @param file The image file to upload
     * @throws IOException If an I/O error occurs
     */
    public void uploadImage(Long animalId, MultipartFile file) throws IOException {
        Animals animal = animalsService.findAnimalById(animalId);

        Path filePath = Path.of(avatarsDir, animalId + "." + getExtension(file.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try
                (InputStream is = file.getInputStream();
                 OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                 BufferedInputStream bis = new BufferedInputStream(is, 1024);
                 BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
                ) {
            bis.transferTo(bos);
        }

        AnimalAvatar animalAvatar = findAnimalAvatarById(animalId);
        if (animalAvatar==null){
            animalAvatar= new AnimalAvatar();
        }
        animalAvatar.setAnimal(animal);
        animalAvatar.setFilePath(filePath.toString());
        animalAvatar.setFileSize(file.getSize());
        animalAvatar.setMediaType(file.getContentType());
        animalAvatar.setData(file.getBytes());

        animalAvatarRepository.save(animalAvatar);

    }


    /**
     * Finds an animal avatar by ID.
     *
     * @param animalId The ID of the animal avatar to find
     * @return The found animal avatar, or null if not found
     */
    public AnimalAvatar findAnimalAvatarById(Long animalId) {
        AnimalAvatar animalAvatar =  animalAvatarRepository.findById(animalId).orElse(null);
        log.info("Was invoked method for findAnimalAvatarById");
        if (animalAvatar == null) {
            log.error("There is no animal avatar with id = {}", animalId);
        }

        return animalAvatar;
    }



    /**
     * Generates a preview image from the given file path.
     *
     * @param filePath The file path of the image to generate a preview for
     * @return The generated preview image as a byte array
     * @throws IOException If an I/O error occurs
     */
    private byte[] generateImagePreview(Path filePath) throws IOException {

        try ( InputStream is = Files.newInputStream(filePath);
              BufferedInputStream bis = new BufferedInputStream(is, 1024);
              ByteArrayOutputStream baos = new ByteArrayOutputStream())
        {
            BufferedImage image = ImageIO.read(bis);

            int height  = image.getHeight() / (image.getWidth()/100);
            BufferedImage preview  = new BufferedImage(100, height, image.getType());
            Graphics2D graphics = preview.createGraphics();
            graphics.drawImage(image, 0,0,100, height, null);
            graphics.dispose();

            ImageIO.write(preview, getExtension(filePath.getFileName().toString()), baos);
            return baos.toByteArray();
        }
    }



    /**
     * Gets the file extension from a filename.
     *
     * @param filename The filename to get the extension for
     * @return The file extension
     */
    private String getExtension(String filename){
        return filename.substring(filename.lastIndexOf(".") + 1);
    }

}
