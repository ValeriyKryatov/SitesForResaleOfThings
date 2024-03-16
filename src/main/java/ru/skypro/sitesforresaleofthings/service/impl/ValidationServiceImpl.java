package ru.skypro.sitesforresaleofthings.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.sitesforresaleofthings.dto.CreateOrUpdateAdDTO;
import ru.skypro.sitesforresaleofthings.dto.CreateOrUpdateCommentDTO;
import ru.skypro.sitesforresaleofthings.dto.Register;
import ru.skypro.sitesforresaleofthings.service.ValidationService;

/**
 * Реализация логики по работе с валидацией
 */
@Service
public class ValidationServiceImpl implements ValidationService {

    @Override
    public boolean validate(Object object) {
        if (object instanceof CreateOrUpdateAdDTO) {
            return ((CreateOrUpdateAdDTO) object).getDescription() != null
                    && ((CreateOrUpdateAdDTO) object).getTitle() != null
                    && ((CreateOrUpdateAdDTO) object).getPrice() != 0;
        } else if (object instanceof Register) {
            return ((Register) object).getUsername() != null
                    && ((Register) object).getPassword() != null
                    && ((Register) object).getFirstName() != null
                    && ((Register) object).getLastName() != null
                    && ((Register) object).getPhone() != null;
        } else if (object instanceof CreateOrUpdateCommentDTO) {
            return ((CreateOrUpdateCommentDTO) object).getText() != null;
        }
        return false;
    }
}