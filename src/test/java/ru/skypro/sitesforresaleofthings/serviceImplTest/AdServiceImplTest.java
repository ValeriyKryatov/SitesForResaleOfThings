package ru.skypro.sitesforresaleofthings.serviceImplTest;

import org.junit.jupiter.api.Test;
import ru.skypro.sitesforresaleofthings.dto.AdDTO;
import ru.skypro.sitesforresaleofthings.dto.AdsDTO;
import ru.skypro.sitesforresaleofthings.entity.AdEntity;
import ru.skypro.sitesforresaleofthings.mapper.AdMapper;
import ru.skypro.sitesforresaleofthings.repository.AdRepository;
import ru.skypro.sitesforresaleofthings.repository.UserRepository;
import ru.skypro.sitesforresaleofthings.service.ImageService;
import ru.skypro.sitesforresaleofthings.service.ValidationService;
import ru.skypro.sitesforresaleofthings.service.impl.AdServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AdServiceImplTest {

    @Test
    public void testGetAllAds() {

        ValidationService validationServiceMock = mock(ValidationService.class);
        AdRepository adRepositoryMock = mock(AdRepository.class);
        UserRepository userRepositoryMock = mock(UserRepository.class);
        AdMapper adMapperMock = mock(AdMapper.class);
        ImageService imageServiceMock = mock(ImageService.class);
        AdServiceImpl adsService = new AdServiceImpl(validationServiceMock, adRepositoryMock, userRepositoryMock, adMapperMock, imageServiceMock);

        AdDTO adDTO1 = new AdDTO();
        AdDTO adDTO2 = new AdDTO();
        List<AdDTO> adsDtoList = Arrays.asList(adDTO1, adDTO2);

        when(adRepositoryMock.findAll()).thenReturn(Arrays.asList(new AdEntity(), new AdEntity()));
        when(adMapperMock.mapToDTO(any(AdEntity.class))).thenReturn(new AdDTO());

        AdsDTO adsDTO = adsService.getAllAds();

        assertEquals(2, adsDTO.getCount());
        assertEquals(adsDtoList, adsDTO.getResults());
    }
}