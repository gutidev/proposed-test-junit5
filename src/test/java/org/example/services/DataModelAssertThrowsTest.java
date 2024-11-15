package org.example.services;

import org.example.model.TolkienCharacter;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.example.model.Race.HOBBIT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DataModelAssertThrowsTest {

    @Test
    @DisplayName("Ensure that access to the fellowship throws exception outside the valid range")
    void exceptionTesting() {
        DataService dataService = new DataService();
        Throwable exception = assertThrows(
                IndexOutOfBoundsException.class,
                () -> dataService.getFellowship().get(20)
        );
        assertEquals("Index 20 out of bounds for length 9", exception.getMessage());
    }

    @Test
    public void ageMustBeLargerThanZeroViaSetterTest() {
        TolkienCharacter frodo = new TolkienCharacter("Frodo", 33, HOBBIT);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> frodo.setAge(-1)
        );
        assertEquals("Age is not allowed to be smaller than zero", exception.getMessage());
    }

    @Test
    @Disabled("Please fix and enable")
    public void ageMustBeLargerThanZeroViaConstructorTest() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new TolkienCharacter("Frodo", -1, HOBBIT)
        );
        assertEquals("Age is not allowed to be smaller than zero", exception.getMessage());
    }

}