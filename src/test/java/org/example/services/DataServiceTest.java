package org.example.services;

import static org.example.model.Race.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.example.model.Movie;
import org.example.model.TolkienCharacter;
import org.junit.jupiter.api.*;


class DataServiceTest {

    DataService dataService;

    @BeforeEach
    public void setUp() {
        dataService = new DataService();
    }

    @Test
    void ensureThatInitializationOfTolkienCharactersWorks() {
        TolkienCharacter frodo = new TolkienCharacter("Frodo", 33, HOBBIT);
        assertEquals(33, frodo.age);
        assertEquals("Frodo", frodo.getName());
        assertNotEquals("Frodon", frodo.getName());
    }

    @Test
    void ensureThatEqualsWorksForCharaters() {
        Object jake = new TolkienCharacter("Jake", 43, HOBBIT);
        Object sameJake = jake;
        Object jakeClone = new TolkienCharacter("Jake", 12, HOBBIT);

        assertEquals(jake, sameJake);
        assertNotEquals(jake, jakeClone);
    }

    @Test
    void checkInheritance() {
        TolkienCharacter tolkienCharacter = dataService.getFellowship().get(0);
        assertEquals(TolkienCharacter.class, tolkienCharacter.getClass());
        assertNotEquals(Movie.class, tolkienCharacter.getClass());
    }

    @Test
    void ensureFellowShipCharacterAccessByNameReturnsNullForUnknownCharacter() {
        assertNull(dataService.getFellowshipCharacter("Lars"));
    }

    @Test
    void ensureFellowShipCharacterAccessByNameWorksGivenCorrectNameIsGiven() {
        TolkienCharacter character = dataService.getFellowshipCharacter("Frodo");
        assertEquals("Frodo", character.getName());
    }


    @Test
    void ensureThatFrodoAndGandalfArePartOfTheFellowship() {
        assertTrue(dataService
                .getFellowship()
                .stream()
                .anyMatch(x -> Objects.equals(x.getName(), "Frodo")));

        assertTrue(dataService
                .getFellowship()
                .stream()
                .anyMatch(x -> Objects.equals(x.getName(), "Gandalf")));
    }

    @Test
    @DisplayName("Test that at least one ring bearer is part of the fellowship")
    void ensureThatOneRingBearerIsPartOfTheFellowship() {
        assertTrue(dataService
                .getFellowship()
                .stream()
                .anyMatch(character -> dataService.getRingBearers().containsValue(character)));
    }


    @Tag("slow")
    @DisplayName("Minimal stress testing: run this test 1000 times to ")
    @RepeatedTest(1000)
    void ensureThatWeCanRetrieveFellowshipMultipleTimes() {
        assertNotNull(dataService.getFellowship());
    }

    @Test
    void ensureOrdering() {
        List<TolkienCharacter> fellowship = dataService.getFellowship();
        List<String> orderedCharacters = Arrays.asList(
                "Frodo",
                "Sam",
                "Merry",
                "Pippin",
                "Gandalf",
                "Legolas",
                "Gimli",
                "Aragorn",
                "Boromir"
        );
        for (int i = 0; i < fellowship.size(); i++) {
            assertEquals(fellowship.get(i).getName(), orderedCharacters.get(i));
        }
    }

    @TestFactory
    Stream<DynamicTest> ensureOrderingInDynamicForm() {
        List<TolkienCharacter> fellowship = dataService.getFellowship();
        List<String> orderedCharacters = Arrays.asList(
                "Frodo",
                "Sam",
                "Merry",
                "Pippin",
                "Gandalf",
                "Legolas",
                "Gimli",
                "Aragorn",
                "Boromir"
        );
        return IntStream
                .iterate(0, index -> index+1).limit(9)
                .mapToObj(index -> DynamicTest.dynamicTest(
                        "Test: "+ index,
                        () -> assertEquals(fellowship.get(index).getName(), orderedCharacters.get(index))
                ));
    }

    @Test
    void ensureAge() {
        List<TolkienCharacter> fellowship = dataService.getFellowship();

        assertTrue(
                fellowship
                .stream()
                .filter(x -> x.getRace() == HOBBIT || x.getRace() == MAN)
                .allMatch(x -> x.age < 100)
        );

        assertTrue(
                fellowship
                        .stream()
                        .filter(x -> x.getRace() == ELF || x.getRace() == DWARF || x.getRace() == MAIA)
                        .allMatch(x -> x.age > 100)
        );
    }

    @Test
    void ensureThatFellowsStayASmallGroup() {
        IndexOutOfBoundsException t = assertThrows(
                IndexOutOfBoundsException.class,
                () -> dataService.getFellowship().get(20),
                "Expected action() to throw, but it didn't"
        );

        assertEquals(IndexOutOfBoundsException.class, t.getClass());
        assertEquals("Index 20 out of bounds for length 9", t.getMessage());
    }

    @Test
    void ensureUpdateDoesNotRunLongerTest() {
        boolean response = assertTimeout(
                Duration.ofSeconds(3),
                () -> dataService.update()
        );

        assertEquals(true, response);
    }

}