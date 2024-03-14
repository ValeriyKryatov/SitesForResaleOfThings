package ru.skypro.sitesforresaleofthings.entityTest;

import org.junit.jupiter.api.Test;
import ru.skypro.sitesforresaleofthings.entity.AdEntity;
import ru.skypro.sitesforresaleofthings.entity.CommentEntity;
import ru.skypro.sitesforresaleofthings.entity.UserEntity;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdEntityTest {

    @Test
    public void testGetId() {
        AdEntity ad = new AdEntity(1, new UserEntity(), "/image/test.jpg", 500, "testTitle", "testDescription", null);
        assertEquals((Integer) 1, ad.getId());
    }

    @Test
    public void testSetTitle() {
        AdEntity ad = new AdEntity();
        ad.setTitle("newTestTitle");
        assertEquals("newTestTitle", ad.getTitle());
    }

    @Test
    public void testGetComments() {
        AdEntity ad = new AdEntity();
        CommentEntity comment1 = new CommentEntity();
        CommentEntity comment2 = new CommentEntity();
        ad.setComments(Arrays.asList(comment1, comment2));
        assertEquals(2, ad.getComments().size());
    }

    @Test
    public void testSetAuthor() {
        AdEntity ad = new AdEntity();
        UserEntity user = new UserEntity();
        ad.setAuthor(user);
        assertEquals(user, ad.getAuthor());
    }
}