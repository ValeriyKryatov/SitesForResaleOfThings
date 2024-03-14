package ru.skypro.sitesforresaleofthings.DTOTest;

import org.junit.jupiter.api.Test;
import ru.skypro.sitesforresaleofthings.dto.CreateOrUpdateCommentDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CreateOrUpdateCommentDTOTest {

    @Test
    public void testCreateComment() {
        CreateOrUpdateCommentDTO comment = new CreateOrUpdateCommentDTO("test");
        assertEquals("test", comment.getText());
    }

    @Test
    public void testSetText() {
        CreateOrUpdateCommentDTO comment = new CreateOrUpdateCommentDTO();
        comment.setText("test1");
        assertEquals("test1", comment.getText());
    }

    @Test
    public void testNoArgsConstructor() {
        CreateOrUpdateCommentDTO comment = new CreateOrUpdateCommentDTO();
        assertNull(comment.getText());
    }
}