package com.vishnu.assgame.application.service;

import com.vishnu.assgame.domain.model.Player;
import com.vishnu.assgame.domain.model.PlayerType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DuplicateNameValidatorTest {

    private final DuplicateNameValidator validator = new DuplicateNameValidator();

    @Test
    void shouldReturnErrorWhenDuplicateNamesExist() {
        List<Player> players = List.of(
                new Player("Vishnu", PlayerType.HUMAN),
                new Player("Avani", PlayerType.AI),
                new Player("VISHNU", PlayerType.AI),
                new Player("Priya", PlayerType.HUMAN)
        );

        List<String> errors = validator.validate(players);

        assertEquals(1, errors.size());
        assertEquals("Duplicate player name 'Vishnu'", errors.getFirst());
    }

    @Test
    void shouldReturnNoErrorWhenAllNamesAreUnique() {
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
