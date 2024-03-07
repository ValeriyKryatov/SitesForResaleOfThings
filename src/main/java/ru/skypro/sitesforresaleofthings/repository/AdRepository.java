package ru.skypro.sitesforresaleofthings.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.sitesforresaleofthings.entity.AdEntity;
import ru.skypro.sitesforresaleofthings.entity.UserEntity;

import java.util.List;

/**
 * Создаем репозиторий для сущности "Объявление"
 */

@Repository
public interface AdRepository extends JpaRepository<AdEntity, Integer> {

    /**
     * Поиск объявлений по автору
     * @param author автор объявления
     * @return список найденных объявлений
     */
    List<AdEntity> findByAuthor(UserEntity author);
}