package ru.skypro.sitesforresaleofthings.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.sitesforresaleofthings.dto.AdDTO;
import ru.skypro.sitesforresaleofthings.dto.AdsDTO;
import ru.skypro.sitesforresaleofthings.dto.CreateOrUpdateAdDTO;
import ru.skypro.sitesforresaleofthings.dto.ExtendedAdDTO;

/**
 * Создаем интерфейс-сервис по работе с объявлениями
 */
public interface AdService {

    /**
     * Получить все объявления
     *
     * @return объявления
     */
    AdsDTO getAllAds();

    /**
     * Добавить объявление
     *
     * @param dto         тело запроса
     * @param image       картинка товара
     * @param userDetails информация о пользователе
     * @return информация об объявлении
     */
    AdDTO addAd(CreateOrUpdateAdDTO dto, MultipartFile image, String userDetails);

    /**
     * Получить информацию об объявлении по id
     *
     * @param pk идентификатор объявления
     * @return информация об объявлении
     */
    ExtendedAdDTO getFullAdsById(Integer pk);

    /**
     * Удалить объявление по id
     *
     * @param pk          идентификатор объявления
     * @param userDetails информация о пользователе
     * @return true or false
     */
    boolean deleteAdById(Integer pk, String userDetails);

    /**
     * Обновить информацию об объявлении по id
     *
     * @param pk          идентификатор объявления
     * @param dto         тело изменения
     * @param userDetails информация о пользователе
     * @return информация об объявлении
     */
    AdDTO updateAdsById(Integer pk, CreateOrUpdateAdDTO dto, String userDetails);

    /**
     * Получить объявления авторизованного пользователя
     *
     * @param userDetails информация о пользователе
     * @return объявления пользователя
     */
    AdsDTO getAdsMe(String userDetails);

    /**
     * Изменить картинку объявления по его идентификатору
     *
     * @param pk    идентификатор объявления
     * @param image новая картинка объявления
     * @return измененная картинка объявления
     */
    boolean updateAdImage(Integer pk, MultipartFile image);
}
