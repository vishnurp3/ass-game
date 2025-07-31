package com.vishnu.assgame.application.service;

import com.vishnu.assgame.domain.model.Player;
import com.vishnu.assgame.domain.model.PlayerType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TotalCountValidatorTest {

    private final TotalCountValidator validator = new TotalCountValidator();

    @Test
    void shouldReturnErrorWhenLessThanFourPlayers() {
        List<Player> players = List.of(
                new Player("Vishnu", PlayerType.HUMAN),
                new Player("Avani", PlayerType.AI),
                new Player("Rahul", PlayerType.AI)
        );

        List<String> errors = validator.validate(players);

        assertEquals(1, errors.size());
        assertEquals("Minimum 4 players required", errors.getFirst());
    }

    @Test
    void shouldReturnErrorWhenMoreThanEightPlayers() {
        List<Player> players = List.of(
                new Player("P1", PlayerType.AI),
                new Player("P2", PlayerType.AI),
                new Player("P3", PlayerType.AI),
                new Player("P4", PlayerType.AI),
                new Player("P5", PlayerType.AI),
                new Player("P6", PlayerType.AI),
                new Player("P7", PlayerType.AI),
                new Player("P8", PlayerType.AI),
                new Player("P9", PlayerType.AI)
        );

        List<String> errors = validator.validate(players);

        assertEquals(1, errors.size());
        assertEquals("Maximum 8 players allowed", errors.getFirst());
    }

    @Test
    void shouldReturnNoErrorWhenPlayerCountBetweenFourAndEight() {
        List<Player> players = List.of(
                new Player("Vishnu", PlayerType.HUMAN),
                new Player("Avani", PlayerType.AI),
                new Player("Rahul", PlayerType.AI),
                new Player("Priya", PlayerType.HUMAN)
        );

        List<String> errors = validator.validate(players);

        assertTrue(errors.isEmpty());
    }
}
