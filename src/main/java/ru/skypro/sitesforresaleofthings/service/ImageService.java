package ru.skypro.sitesforresaleofthings.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.sitesforresaleofthings.entity.ImageEntity;

import java.io.IOException;

/**
 * Создаем интерфейс-сервис по работе с картинками
 */
public interface ImageService {
    /**
     * Загружаем новое изображение
     * @param image новое изображение
     * @return название файла
     */
    String addImage(MultipartFile image);

    /**
     * Загружает изображение по названию изображения
     *
     * @param fileName название изображения
     * @return изображение в виде byte[]
     */
    byte[] loadImage(String fileName);

    byte[] loadImageFail(String fileName);
}