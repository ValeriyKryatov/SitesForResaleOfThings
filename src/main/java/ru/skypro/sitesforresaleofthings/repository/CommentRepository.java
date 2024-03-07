package ru.skypro.sitesforresaleofthings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
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
     * @param pk идентификатор объявления
     * @return список найденных комментариев
     */
    List<CommentEntity> findByAdPk(Integer pk);
}