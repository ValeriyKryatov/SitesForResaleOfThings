package ru.skypro.sitesforresaleofthings.service;

import org.springframework.web.multipart.MultipartFile;

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
     * @param fileName название картинки
     * @return картинка в виде byte[]
     */
    byte[] loadImage(String fileName);

    byte[] loadImageFail(String fileName);
}