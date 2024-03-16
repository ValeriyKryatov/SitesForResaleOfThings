package ru.skypro.sitesforresaleofthings.DTOTest;

import org.junit.jupiter.api.Test;
import ru.skypro.sitesforresaleofthings.dto.AdDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AdDTOTest {

    @Test
    public void testDataClass() {

        AdDTO ad = new AdDTO(1, "https://test.com/image.jpg", 99, 2000, "testTitle");
        assertEquals(1, ad.getAuthor());
        assertEquals("https://test.com/image.jpg", ad.getImage());
        assertEquals(99, ad.getPk());
        assertEquals(2000, ad.getPrice());
        assertEquals("testTitle", ad.getTitle());
        AdDTO emptyAds = new AdDTO();
        assertNull(emptyAds.getAuthor());
        assertNull(emptyAds.getImage());
        assertNull(emptyAds.getPk());
        assertNull(emptyAds.getPrice());
        assertNull(emptyAds.getTitle());
    }
}