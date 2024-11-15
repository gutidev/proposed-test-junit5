package org.example.nested;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class UsingNestedTests {

    private List<String> list;

    @BeforeEach
    void setup() {
        list = Arrays.asList("JUnit 5", "Mockito");
    }

    @Test
    void listTests() {
        assertEquals(2, list.size());
    }

    @Test
    void checkFirstElement() {
        assertEquals(("JUnit 5"), list.get(0));
    }

    @Test
    void checkSecondElement() {
        assertEquals(("Mockito"), list.get(1));
    }


    @DisplayName("Grouped tests for checking members")
    @Nested
    class CheckMembers {
        @BeforeEach
        void setup() {
            list = Arrays.asList("Java", "Test");
        }

        @Test
        void checkFirstElement() {
            assertEquals(("Java"), list.get(0));
        }

        @Test
        void checkSecondElement() {
            assertEquals(("Test"), list.get(1));
        }
    }

}