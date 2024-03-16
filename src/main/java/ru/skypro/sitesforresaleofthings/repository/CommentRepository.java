package ru.skypro.sitesforresaleofthings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skypro.sitesforresaleofthings.entity.CommentEntity;

import java.util.List;

/**
 * Создаем репозиторий для сущности "Комментарий"
 */

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

    /**
     * Поиск комментария по идентификатору объявления
     * @param id идентификатор объявления
     * @return список найденных комментариев
     */
    List<CommentEntity> findByAdEntityId(Integer id);

    /**
     * Изменить комментарий
     *
     * @param commentId идентификатор комментария
     * @return сущность комментария
     */
    @Modifying
    @Query("UPDATE CommentEntity c SET " +
            "c.author.id = :authorId, " +
            "c.author.image = :authorImage, " +
            "c.author.firstName = :firstName, " +
            "c.createdAt = :created_at, " +
            "c.text = :text " +
            "WHERE c.id = :comment_id")
    CommentEntity updateCommentById(
            @Param("created_at") Integer createdAt,
            @Param("firstName") String firstName,
            @Param("authorImage") String authorImage,
            @Param("authorId") Integer authorId,
            @Param("text") String text,
            @Param("comment_id") Integer commentId);
}