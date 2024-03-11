package ru.skypro.sitesforresaleofthings.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.sitesforresaleofthings.entity.ImageEntity;

import java.io.IOException;

/**
 * Создаем интерфейс-сервис по работе с картинками
 */
public interface ImageService {
    /**
     * Загружает новую картинку
     * @param image новая картинка
     * @return название файла
     */
    String addImage(MultipartFile image);

    /**
     * Загружает картинку по названию картинки
     *
     * @param fileName название изображения
     * @return изображение в виде byte[]
     */
    byte[] loadImage(String fileName);

    byte[] loadImageFile(String fileName);
}