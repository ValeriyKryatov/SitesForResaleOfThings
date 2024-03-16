package ru.skypro.sitesforresaleofthings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skypro.sitesforresaleofthings.entity.UserEntity;

/**
 * Создаем репозиторий для сущности "Пользователь"
 */

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    /**
     * Поиск пользователя по логину
     * @param username логин пользователя
     * @return найденный пользователь
     */
    UserEntity findByUsername(String username);

    @Modifying
    @Query("UPDATE UserEntity u SET " +
            "u.password = :newPassword " +
            "WHERE u.id = :id")
    UserEntity updatePassword(
            @Param("id") Integer id,
            @Param("newPassword") String newPassword);

    @Modifying
    @Query("UPDATE UserEntity SET " +
            "firstName = :first_name, " +
            "lastName = :last_name," +
            "phone = :phone," +
            "email = :email," +
            "image = :image" +
            " WHERE id = :id")
    UserEntity updateUser(
            @Param("first_name") String firstName,
            @Param("last_name") String lastName,
            @Param("phone") String phone,
            @Param("email") String email,
            @Param("image") String image,
            @Param("id") Integer id);
}