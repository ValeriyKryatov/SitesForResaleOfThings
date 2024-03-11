package ru.skypro.sitesforresaleofthings.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

/**
 * Создаем сущность "Объявление"
 */
@Entity
/**
 * Создаем таблицу ads(Объявления), имеющую следующие свойства-колонки:
 * 1) pk - id объявления,
 * 2) author - id автора объявления,
 * 3) image - ссылка на картинку объявления,
 * 4) price - цена объявления,
 * 5) title - заголовок объявления,
 * 6) description - описание объявления,
 * 7) comments - комментарии объявления
 */
@Table(name = "ads")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private UserEntity author;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "price")
    private Integer price;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "adEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<CommentEntity> comments;
}