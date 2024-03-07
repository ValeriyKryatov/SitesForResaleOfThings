package ru.skypro.sitesforresaleofthings.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.sitesforresaleofthings.constant.Role;
import ru.skypro.sitesforresaleofthings.dto.AdDTO;
import ru.skypro.sitesforresaleofthings.dto.AdsDTO;
import ru.skypro.sitesforresaleofthings.dto.CreateOrUpdateAdDTO;
import ru.skypro.sitesforresaleofthings.dto.ExtendedAdDTO;
import ru.skypro.sitesforresaleofthings.entity.AdEntity;
import ru.skypro.sitesforresaleofthings.entity.UserEntity;
import ru.skypro.sitesforresaleofthings.exception.NotFoundEntityException;
import ru.skypro.sitesforresaleofthings.exception.ValidationException;
import ru.skypro.sitesforresaleofthings.mapper.AdMapper;
import ru.skypro.sitesforresaleofthings.repository.AdRepository;
import ru.skypro.sitesforresaleofthings.repository.UserRepository;
import ru.skypro.sitesforresaleofthings.service.AdService;
import ru.skypro.sitesforresaleofthings.service.ImageService;
import ru.skypro.sitesforresaleofthings.service.ValidationService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация логики по работе с объявлениями
 */
@Slf4j
@Service
@AllArgsConstructor
public class AdServiceImpl implements AdService {

    private final ValidationService validationService;
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final AdMapper adMapper;
    private final ImageService imageService;

    @Override
    public AdsDTO getAllAds() {
        List<AdDTO> adDTO = adRepository.findAll().stream()
                .map(adMapper::mapToDTO)
                .collect(Collectors.toList());
        return new AdsDTO(adDTO.size(), adDTO);
    }

    @Override
    public AdDTO addAd(CreateOrUpdateAdDTO dto, MultipartFile image, String userDetails) {

        if (!validationService.validate(dto)) {
            throw new ValidationException(dto.toString());
        }
        UserEntity user = userRepository.findByUsername(userDetails);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        AdEntity entity = adMapper.mapToEntity(dto, user);
        String imageId = imageService.addImage(image);
        entity.setImagePath(imageId);
        adRepository.save(entity);
        AdDTO adDTO = adMapper.mapToDTO(entity);
        return adDTO;
    }

    @Override
    public ExtendedAdDTO getFullAdsById(Integer pk) {
        AdEntity entity = adRepository.findById(pk)
                .orElseThrow(() -> new NotFoundEntityException("Сущность не найдена!"));
        ExtendedAdDTO dto = adMapper.adEntityMapToDTO(entity);
        return dto;
    }

    @Override
    public boolean deleteAdById(Integer pk, String userDetails) {
        UserEntity authorOrAdmin = userRepository.findByUsername(userDetails);
        AdEntity entity = adRepository.findById(pk)
                .orElseThrow(() -> new NotFoundEntityException("Сущность не найдена!"));
        if (entity.getAuthor().getUsername().equals(userDetails)
                || authorOrAdmin.getRole() == Role.ADMIN) {
            adRepository.deleteById(pk);
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @Override
    @Transactional
    public AdDTO updateAdsById(Integer pk, CreateOrUpdateAdDTO dto, String userDetails) {
        if (!validationService.validate(dto)) {
            throw new ValidationException(dto.toString());
        }
        UserEntity authorOrAdmin = userRepository.findByUsername(userDetails);
        AdEntity entity = adRepository.findById(pk)
                .orElseThrow(() -> new NotFoundEntityException("Сущность не найдена!"));
        if (entity.getAuthor().getUsername().equals(userDetails)
                || authorOrAdmin.getRole() == (Role.ADMIN)) {
            entity.setDescription(dto.getDescription());
            entity.setPrice(dto.getPrice());
            entity.setTitle(dto.getTitle());
            adRepository.save(entity);
            AdDTO adDTO = adMapper.mapToDTO(entity);
            return adDTO;
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public AdsDTO getAdsMe(String userDetails) {
        UserEntity author = userRepository.findByUsername(userDetails);
        if (author != null) {
            List<AdEntity> adEntity = adRepository.findByAuthor(author);
            List<AdDTO> dto = new ArrayList<>();
            for (AdEntity ad : adEntity) {
                dto.add(adMapper.mapToDTO(ad));
            }
            return new AdsDTO(dto.size(), dto);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public boolean updateAdImage(Integer pk, MultipartFile image) {
        String imageId = imageService.addImage(image);
        AdEntity entity = adRepository.findById(pk)
                .orElseThrow(() -> new NotFoundEntityException("Сущность не найдена!"));
        entity.setImagePath(imageId);
        adRepository.save(entity);
        return true;
    }
}