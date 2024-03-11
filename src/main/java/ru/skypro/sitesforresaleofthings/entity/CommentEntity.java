package ru.skypro.sitesforresaleofthings.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;

/**
 * Создаем сущность "Комментарий"
 */
@Entity
/**
 * Создаем таблицу comments(Комментарии), имеющую следующие свойства-колонки:
 * 1) pk - id комментария,
 * 2) author - id автора комментария,
 * 3) text - текст комментария,
 * 4) adEntity - объявление комментария,
 * 5) createdAt - дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970
 */
@Table(name = "comments")
@Data
@NoArgsConstructor
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;

    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "ad_id")
    private AdEntity adEntity;

    @Column(name = "created_at")
    private Instant createdAt;
}