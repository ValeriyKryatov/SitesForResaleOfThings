package ru.skypro.sitesforresaleofthings.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Создаем сущность "Картинка", имеющую свойства:
 * 1) id - идентификатор картинки,
 * 2) data - картинка в байтах
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageEntity {

    private String id;
    private byte[] data;
}