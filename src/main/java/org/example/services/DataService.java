package org.example.services;

import org.example.model.Movie;
import org.example.model.Ring;
import org.example.model.TolkienCharacter;

import java.util.*;

import static org.example.model.Race.*;

/**
 * Init data for unit test
 */
public class DataService {

    static final String ERROR_MESSAGE_EXAMPLE_FOR_ASSERTION = "{} assertion : {}\n";


    // Some of the Lord of the Rings characters :
    final TolkienCharacter frodo = new TolkienCharacter("Frodo", 33, HOBBIT);

    final TolkienCharacter sam = new TolkienCharacter("Sam", 38, HOBBIT);

    final TolkienCharacter merry = new TolkienCharacter("Merry", 36, HOBBIT);

    final TolkienCharacter pippin = new TolkienCharacter("Pippin", 28, HOBBIT);

    final TolkienCharacter gandalf = new TolkienCharacter("Gandalf", 2020, MAIA);

    final TolkienCharacter gimli = new TolkienCharacter("Gimli", 139, DWARF);

    final TolkienCharacter legolas = new TolkienCharacter("Legolas", 1000, ELF);

    final TolkienCharacter aragorn = new TolkienCharacter("Aragorn", 87, MAN);

    final TolkienCharacter boromir = new TolkienCharacter("Boromir", 37, MAN);

    final TolkienCharacter sauron = new TolkienCharacter("Sauron", 50000, MAIA);

    final TolkienCharacter galadriel = new TolkienCharacter("Galadriel", 3000, ELF);

    final TolkienCharacter elrond = new TolkienCharacter("Elrond", 3000, ELF);

    final TolkienCharacter guruk = new TolkienCharacter("Guruk", 20, ORC);


    final Movie theFellowshipOfTheRing = new Movie("the fellowship of the Ring", new Date(), "178 min");

    final Movie theTwoTowers = new Movie("the two Towers", new Date(), "179 min");

    final Movie theReturnOfTheKing = new Movie("the Return of the King", new Date(), "201 min");


    public List<TolkienCharacter> getFellowship() {
        final List<TolkienCharacter> fellowshipOfTheRing = new ArrayList<>();
        // let's do some team building :)
        fellowshipOfTheRing.add(frodo);
        fellowshipOfTheRing.add(sam);
        fellowshipOfTheRing.add(merry);
        fellowshipOfTheRing.add(pippin);
        fellowshipOfTheRing.add(gandalf);
        fellowshipOfTheRing.add(legolas);
        fellowshipOfTheRing.add(gimli);
        fellowshipOfTheRing.add(aragorn);
        fellowshipOfTheRing.add(boromir);
        return fellowshipOfTheRing;
    }

    public List<TolkienCharacter> getOrcsWithHobbitPrisoners() {
        final List<TolkienCharacter> orcsWithHobbitPrisoners = new ArrayList<TolkienCharacter>();
        orcsWithHobbitPrisoners.add(guruk);
        orcsWithHobbitPrisoners.add(merry);
        orcsWithHobbitPrisoners.add(pippin);
        return orcsWithHobbitPrisoners;
    }

    public TolkienCharacter getFellowshipCharacter(String name) {
        List<TolkienCharacter> list = getFellowship();
        return list.stream()
                .filter(s-> Objects.equals(s.getName(), name))
                .findFirst()
                .orElse(null);
    }

    public Map<Ring, TolkienCharacter> getRingBearers() {
        Map<Ring, TolkienCharacter> ringBearers = new HashMap<>();
        // ring bearers
        ringBearers.put(Ring.nenya, galadriel);
        ringBearers.put(Ring.narya, gandalf);
        ringBearers.put(Ring.vilya, elrond);
        ringBearers.put(Ring.oneRing, frodo);
        return  ringBearers;
    }

    public boolean update() {
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

}