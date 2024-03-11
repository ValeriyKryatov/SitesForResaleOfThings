package ru.skypro.sitesforresaleofthings.mapper;

import org.springframework.stereotype.Service;
import ru.skypro.sitesforresaleofthings.dto.CommentDTO;
import ru.skypro.sitesforresaleofthings.dto.CreateOrUpdateCommentDTO;
import ru.skypro.sitesforresaleofthings.entity.AdEntity;
import ru.skypro.sitesforresaleofthings.entity.CommentEntity;
import ru.skypro.sitesforresaleofthings.entity.UserEntity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

/**
 * Создаем сервис(маппер), который устанавливает соответствие(маппит) из сущности в DTO и обратно
 */
@Service
public class CommentMapper {

       public CommentDTO mapToDTO(CommentEntity entity) {

        TimeZone timeZone = TimeZone.getDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(entity.getCreatedAt(), timeZone.toZoneId());

        CommentDTO dto = new CommentDTO();
        dto.setAuthor(entity.getAuthor().getId());
        dto.setAuthorFirstName(entity.getAuthor().getFirstName());
        dto.setPk(entity.getId());
        dto.setCreatedAt(localDateTime);
        dto.setText(entity.getText());
        if (entity.getAuthor().getImage() != null) {
            dto.setAuthorImage(String.format("/ads/image/%s", entity.getAuthor().getImage()));
        } else {
            dto.setAuthorImage(null);
        }
        return dto;
    }

    public CommentEntity mapToEntity(CreateOrUpdateCommentDTO dto, UserEntity author, AdEntity adEntity) {
        CommentEntity entity = new CommentEntity();
        entity.setText(dto.getText());
        entity.setAdEntity(adEntity);
        entity.setAuthor(author);
        entity.setCreatedAt(Instant.now());
        return entity;
    }
}