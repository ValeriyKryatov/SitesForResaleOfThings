package ru.skypro.sitesforresaleofthings.service;

import ru.skypro.sitesforresaleofthings.dto.CommentDTO;
import ru.skypro.sitesforresaleofthings.dto.CommentsDTO;
import ru.skypro.sitesforresaleofthings.dto.CreateOrUpdateCommentDTO;

/**
 * Создаем интерфейс-сервис по работе с комментариями
 */
public interface CommentService {

    /**
     * Создание комментария
     *
     * @param id          идентификатор комментария
     * @param dto         тело создаваемого комментария в виде DTO
     * @param userDetails информация о пользователе
     * @return созданный комментарий
     */
    CommentDTO saveComment(Integer id, CreateOrUpdateCommentDTO dto, String userDetails);

    /**
     * Удалить комментарий
     *
     * @param adId        идентификатор объявления удаляемого комментария
     * @param commentId   идентификатор удаляемого комментария
     * @param userDetails информация о пользователе
     * @return true or false
     */
    boolean deleteComment(Integer adId, Integer commentId, String userDetails);

    /**
     * Изменить комментарий
     *
     * @param adId        идентификатор объявления изменяемого комментария
     * @param commentId   идентификатор изменяемого комментария
     * @param dto         тело измененного комментария
     * @param userDetails информация о пользователе
     * @return измененный комментарий
     */
    CommentDTO updateComment(Integer adId, Integer commentId, CommentDTO dto, String userDetails);

    /**
     * Получить комментарий
     *
     * @param id идентификатор комментария
     * @return комментарий
     */
    CommentsDTO getComments(Integer id);
}