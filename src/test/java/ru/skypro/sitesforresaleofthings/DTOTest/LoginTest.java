package ru.skypro.sitesforresaleofthings.DTOTest;

import org.junit.jupiter.api.Test;
import ru.skypro.sitesforresaleofthings.dto.Login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LoginTest {

    @Test
    public void testGetterAndSetter() {
        Login login = new Login();
        login.setPassword("testPassword");
        login.setUsername("testUsername");

        assertEquals("testPassword", login.getPassword());
        assertEquals("testUsername", login.getUsername());
    }

    @Test
    public void testAllArgsConstructor() {
        Login login = new Login("testUsername", "testPassword");

        assertEquals("testPassword", login.getPassword());
        assertEquals("testUsername", login.getUsername());
    }

    @Test
    public void testNoArgsConstructor() {
        Login login = new Login();

        assertNull(login.getPassword());
        assertNull(login.getUsername());
    }
}