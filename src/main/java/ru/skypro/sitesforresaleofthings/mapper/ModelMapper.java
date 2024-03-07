package ru.skypro.sitesforresaleofthings.mapper;

/**
 * Модель по работе с маппингом
 *
 * @param <T>
 * @param <R>
 */
public interface ModelMapper<T, R> {

    /**
     * Маппинг сущности в дто
     *
     * @param entity сущность
     * @return дто
     */
    T mapToDTO(R entity);

    /**
     * Маппинг дто в сущность
     *
     * @param dto дто
     * @return сущность
     */
    R mapToEntity(T dto);
}