package ru.skypro.sitesforresaleofthings.exception;

/**
 * Исключение отсутствия сущности
 */
public class NotFoundEntityException extends RuntimeException {

    public NotFoundEntityException(String message) {
        super(message);
    }
}