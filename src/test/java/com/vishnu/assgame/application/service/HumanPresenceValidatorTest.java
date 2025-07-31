package com.vishnu.assgame.application.service;

import com.vishnu.assgame.domain.model.Player;
import com.vishnu.assgame.domain.model.PlayerType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HumanPresenceValidatorTest {

    private final HumanPresenceValidator validator = new HumanPresenceValidator();

    @Test
    void shouldReturnErrorIfNoHumanPlayersPresent() {
        List<Player> players = List.of(
                new Player("Bot1", PlayerType.AI),
                new Player("Bot2", PlayerType.AI),
                new Player("Bot3", PlayerType.AI),
                new Player("Bot4", PlayerType.AI)
        );

        List<String> errors = validator.validate(players);

        assertEquals(1, errors.size());
        assertEquals("At least one human player required", errors.getFirst());
    }

    @Test
    void shouldReturnNoErrorIfAtLeastOneHumanPresent() {
        List<Player> players = List.of(
                new Player("Bot1", PlayerType.AI),
                new Player("Avani", PlayerType.HUMAN),
                new Player("Bot2", PlayerType.AI),
                new Player("Bot3", PlayerType.AI)
        );

        List<String> errors = validator.validate(players);

        assertTrue(errors.isEmpty());
    }
}
