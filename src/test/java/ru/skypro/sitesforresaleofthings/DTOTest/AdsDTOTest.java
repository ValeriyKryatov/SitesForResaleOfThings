package ru.skypro.sitesforresaleofthings.DTOTest;

import org.junit.jupiter.api.Test;
import ru.skypro.sitesforresaleofthings.dto.AdDTO;
import ru.skypro.sitesforresaleofthings.dto.AdsDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdsDTOTest {

    @Test
    public void testAdsDTO() {

        Integer count = 10;
        List<AdDTO> results = new ArrayList<>();
        results.add(new AdDTO(1, "https://пример.com/image.jpg", 1, 100, "testTitle"));
        results.add(new AdDTO(1, "https://пример.com/image1.jpg", 2, 200, "testTitle"));
        results.add(new AdDTO(1, "https://пример.com/image2.jpg", 3, 300, "testTitle"));
        AdsDTO adsDTO = new AdsDTO(count, results);

        Integer expectedCount = 10;
        Integer actualCount = adsDTO.getCount();

        List<AdDTO> expectedResults = new ArrayList<>();
        expectedResults.add(new AdDTO(1, "https://пример.com/image.jpg", 1, 100, "testTitle"));
        expectedResults.add(new AdDTO(1, "https://пример.com/image1.jpg", 2, 200, "testTitle"));
        expectedResults.add(new AdDTO(1, "https://пример.com/image2.jpg", 3, 300, "testTitle"));
        List<AdDTO> actualResults = adsDTO.getResults();

        assertEquals(expectedCount, actualCount);
        assertEquals(expectedResults, actualResults);
    }
}