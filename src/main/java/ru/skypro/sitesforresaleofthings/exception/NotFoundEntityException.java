package ru.skypro.sitesforresaleofthings.exception;

/**
 * Ошибка отсутствия сущности.
 */
public class NotFoundEntityException extends RuntimeException {

    public NotFoundEntityException(String message) {
        super(message);
    }
}