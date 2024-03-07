package ru.skypro.sitesforresaleofthings.service;


/**
 * Создаем интерфейс-сервис по работе с валидацией
 */
public interface ValidationService {

    /**
     * Валидация сущностей
     *
     * @param object сущность
     * @return валидная сущность
     */
    boolean validate(Object object);
}