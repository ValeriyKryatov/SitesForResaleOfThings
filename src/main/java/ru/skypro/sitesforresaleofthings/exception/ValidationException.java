package ru.skypro.sitesforresaleofthings.exception;

/**
 * Исключение Ошибки валидации
 */
public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super("Ошибка валидации!");
    }
}