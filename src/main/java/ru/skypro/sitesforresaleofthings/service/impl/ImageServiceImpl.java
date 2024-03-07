package ru.skypro.sitesforresaleofthings.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.sitesforresaleofthings.entity.ImageEntity;
import ru.skypro.sitesforresaleofthings.service.ImageService;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import java.io.File;
import java.nio.file.Paths;
import java.net.URL;
import java.lang.String;

import static java.nio.file.Files.*;

/**
 * Реализация логики по работе с картинками
 */
@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final String desktopPath = System.getProperty("user.dir") + File.separator + "images";

    @Override
    public String addImage(MultipartFile image) {

        ImageEntity imageEntity = new ImageEntity();
        try {
            String fileName = UUID.randomUUID() + type(image);
            imageEntity.setId(fileName);
            createDirectories(Paths.get(desktopPath));
            image.transferTo(new File(desktopPath + File.separator + fileName));
            log.info("Image file created by  name: {}", fileName);
        } catch (IOException e) {
            log.error("Error while saving image file{}", imageEntity.getId());
        }
        return imageEntity.getId();
    }

    @Override
    public byte[] loadImage(String fileName) {

        File image;
        byte[] outputFileBytes = null;
        try {
            image = new File(desktopPath, fileName);
            if (exists(image.toPath())) {
                outputFileBytes = readAllBytes(image.toPath());
                log.info("loadImage: File loaded successfully");
            } else {
                try (InputStream in = new URL("").openStream()) {
                    outputFileBytes = in.readAllBytes();
                    log.info("loadImage: File loaded default successfully");
                }
            }
        } catch (IOException e) {
            log.error("file load error");
        }
        return outputFileBytes;
    }

    @Override
    public byte[] loadImageFail(String fileName) {

        File image;
        byte[] outputFileBytes = null;
        try {
            image = new File(desktopPath, fileName);
            outputFileBytes = readAllBytes(image.toPath());
            log.info("loadImageFail: File loaded successfully");
        } catch (IOException e) {
            log.error("loadImageFail: Error while loading file {}", fileName);
        }
        return outputFileBytes;
    }

    private String type(MultipartFile image) {

        String type = image.getContentType();
        assert type != null;
        type = type.replace("image/", ".");

        return type;
    }
}