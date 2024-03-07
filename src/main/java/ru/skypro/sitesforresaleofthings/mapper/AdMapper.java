package ru.skypro.sitesforresaleofthings.mapper;

import org.springframework.stereotype.Service;
import ru.skypro.sitesforresaleofthings.dto.AdDTO;
import ru.skypro.sitesforresaleofthings.dto.CreateOrUpdateAdDTO;
import ru.skypro.sitesforresaleofthings.dto.ExtendedAdDTO;
import ru.skypro.sitesforresaleofthings.entity.AdEntity;
import ru.skypro.sitesforresaleofthings.entity.UserEntity;

/**
 * Создаем сервис(маппер), который устанавливает соответствие(маппит) из сущности в DTO и обратно
 */

@Service
public class AdMapper {

    public AdEntity mapToEntity(CreateOrUpdateAdDTO dto, UserEntity author) {
        AdEntity adEntity = new AdEntity();
        adEntity.setDescription(dto.getDescription());
        adEntity.setTitle(dto.getTitle());
        adEntity.setPrice(dto.getPrice());
        adEntity.setAuthor(author);
        return adEntity;
    }

    public AdDTO mapToDTO(AdEntity entity) {
        AdDTO dto = new AdDTO();
        dto.setPk(entity.getPk());
        dto.setAuthor(entity.getAuthor().getId());
        dto.setImage(entity.getImagePath());
        dto.setPrice(entity.getPrice());
        dto.setTitle(entity.getTitle());
        if (entity.getImagePath() != null) {
            dto.setImage(String.format("/ads/image/%s", entity.getImagePath()));
        } else {
            dto.setImage(null);
        }
        return dto;
    }

    public ExtendedAdDTO adEntityMapToDTO(AdEntity entity) {
        ExtendedAdDTO extendedAdDTO = new ExtendedAdDTO();
        extendedAdDTO.setPk(entity.getPk());
        extendedAdDTO.setAuthorFirstName(entity.getAuthor().getFirstName());
        extendedAdDTO.setAuthorLastName(entity.getAuthor().getLastName());
        extendedAdDTO.setDescription(entity.getDescription());
        extendedAdDTO.setEmail(entity.getAuthor().getEmail());
        extendedAdDTO.setPhone(entity.getAuthor().getPhone());
        extendedAdDTO.setPrice(entity.getPrice());
        extendedAdDTO.setTitle(entity.getTitle());
        if (entity.getImagePath() != null) {
            extendedAdDTO.setImage(String.format("/ads/image/%s", entity.getImagePath()));
        } else {
            extendedAdDTO.setImage(null);
        }
        return extendedAdDTO;
    }
}