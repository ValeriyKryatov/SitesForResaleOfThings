package ru.skypro.sitesforresaleofthings.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Создаем сущность "Картинка"
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageEntity {

    private String id; // идентификатор картинки
    private byte[] data; // картинка в байтах
}