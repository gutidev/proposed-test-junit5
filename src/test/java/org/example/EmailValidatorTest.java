package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class EmailValidatorTest {

    @Test
    public void ensureAValidEmailTest() {
        assertTrue(EmailValidator.isValidEmail("lars.vogel@gmail.com"));
    }

    @Test
    @DisplayName("Ensure that the usage of a subdomain is still valid, see https://en.wikipedia.org/wiki/Subdomain")
    public void ensureAValidSubdomainTest() {
        assertTrue(EmailValidator.isValidEmail("sub.lars.vogel@gmail.com"));
    }

    @Test
    @DisplayName("Ensure that a missing top level domain returns false")
    public void ensureAnInvalidTLDEmailTest() {
        assertFalse(EmailValidator.isValidEmail("lars.vogel@gmail.&com"));
    }

    @Test
    public void ensureAnInvalidEmailByDoubleDotTest() {
        assertFalse(EmailValidator.isValidEmail("lars.vogel@gmail..com"));
    }

    @Test
    public void ensureAnInvalidEmailWithoutUsernameTest() {
        assertFalse(EmailValidator.isValidEmail("@gmail.com"));
    }

    @Test
    public void emptyEmailTest() {
        assertFalse(EmailValidator.isValidEmail(""));
    }

    @Test
    public void nullEmailTest() {
        assertFalse(EmailValidator.isValidEmail(null));
    }

}