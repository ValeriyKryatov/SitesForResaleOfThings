package ru.skypro.sitesforresaleofthings.DTOTest;

import org.junit.jupiter.api.Test;
import ru.skypro.sitesforresaleofthings.constant.Role;
import ru.skypro.sitesforresaleofthings.dto.Register;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RegisterTest {

    @Test
    void testRegister() {
        Register register = new Register("testUser", "testPassword", "Test", "User", "89998887766", Role.USER);
        assertEquals("testUser", register.getUsername());
        assertEquals("testPassword", register.getPassword());
        assertEquals("Test", register.getFirstName());
        assertEquals("User", register.getLastName());
        assertEquals("89998887766", register.getPhone());
        assertEquals(Role.USER, register.getRole());
    }

    @Test
    void testRegisterEmptyConstructor() {
        Register register = new Register();
        assertNotNull(register);
    }

    @Test
    void testRegisterAllArgsConstructor() {
        Register register = new Register("testUser", "testPassword", "Test", "User", "12345678", Role.USER);
        Register register2 = new Register(
                register.getUsername(),
                register.getPassword(),
                register.getFirstName(),
                register.getLastName(),
                register.getPhone(),
                register.getRole());
        assertEquals(register, register2);
    }
}