package ru.skypro.sitesforresaleofthings.mapper;

import org.springframework.stereotype.Service;
import ru.skypro.sitesforresaleofthings.dto.Register;
import ru.skypro.sitesforresaleofthings.dto.UserDTO;
import ru.skypro.sitesforresaleofthings.entity.UserEntity;

/**
 * Создаем сервис(маппер), который устанавливает соответствие(маппит) из сущности в DTO и обратно
 */
@Service
public class UserMapper {

    public UserEntity mapToEntity(Register dto) {
        UserEntity entity = new UserEntity();
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPhone(dto.getPhone());
        entity.setRole(dto.getRole());
        return entity;
    }

    public UserDTO mapToDTO(UserEntity entity) {
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhone(entity.getPhone());
        if (entity.getImage() != null) {
            dto.setImage(String.format("/users/image/%s", entity.getImage()));
        } else {
            dto.setImage(null);
        }
        return dto;
    }
}