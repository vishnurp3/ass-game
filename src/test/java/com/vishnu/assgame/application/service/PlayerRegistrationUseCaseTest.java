package com.vishnu.assgame.application.service;

import com.vishnu.assgame.application.dto.PlayerRegistrationResult;
import com.vishnu.assgame.domain.model.Player;
import com.vishnu.assgame.domain.model.PlayerType;
import com.vishnu.assgame.infrastructure.DefaultNameGenerationService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerRegistrationUseCaseTest {

    private final PlayerRegistrationUseCase useCase = new PlayerRegistrationUseCase(
            new DefaultNameGenerationService(),
            List.of(
                    new TotalCountValidator(),
                    new HumanPresenceValidator(),
                    new DuplicateNameValidator()
            )
    );

    @Test
    void shouldRegisterFourPlayersWithValidHumanAndAiTypes() {
        List<Player> playersToRegister = List.of(
                new Player("Vishnu", PlayerType.HUMAN),
                new Player("Avani", PlayerType.AI),
                new Player("Rahul", PlayerType.HUMAN),
                new Player("Priya", PlayerType.AI)
        );

        PlayerRegistrationResult result = useCase.register(playersToRegister);

        assertTrue(result.success());
        assertEquals(4, result.registeredPlayers().size());
        assertTrue(result.errors().isEmpty());
    }

    @Test
    void shouldFailRegistrationIfLessThanFourPlayersProvided() {
        List<Player> playersToRegister = List.of(
                new Player("Vishnu", PlayerType.HUMAN),
                new Player("Avani", PlayerType.AI),
                new Player("Rahul", PlayerType.HUMAN)
        );

        PlayerRegistrationResult result = useCase.register(playersToRegister);

        assertFalse(result.success());
        assertEquals(0, result.registeredPlayers().size());
        assertEquals("Minimum 4 players required", result.errors().getFirst());
    }

    @Test
    void shouldFailRegistrationIfMoreThanEightPlayersProvided() {
        List<Player> playersToRegister = List.of(
                new Player("Vishnu", PlayerType.HUMAN),
                new Player("Avani", PlayerType.AI),
                new Player("Rahul", PlayerType.HUMAN),
                new Player("Priya", PlayerType.AI),
                new Player("Ravi", PlayerType.HUMAN),
                new Player("Neha", PlayerType.AI),
                new Player("Kabir", PlayerType.HUMAN),
                new Player("Simran", PlayerType.AI),
                new Player("Manoj", PlayerType.HUMAN)
        );

        PlayerRegistrationResult result = useCase.register(playersToRegister);

        assertFalse(result.success());
        assertEquals(0, result.registeredPlayers().size());
        assertEquals("Maximum 8 players allowed", result.errors().getFirst());
    }

    @Test
    void shouldFailRegistrationIfNoHumanPlayersPresent() {
        List<Player> playersToRegister = List.of(
                new Player("BotAlpha", PlayerType.AI),
                new Player("BotBeta", PlayerType.AI),
                new Player("BotGamma", PlayerType.AI),
                new Player("BotDelta", PlayerType.AI)
        );

        PlayerRegistrationResult result = useCase.register(playersToRegister);

        assertFalse(result.success());
        assertEquals(0, result.registeredPlayers().size());
        assertEquals("At least one human player required", result.errors().getFirst());
    }

    @Test
    void shouldFailRegistrationIfDuplicatePlayerNamesExist() {
        List<Player> playersToRegister = List.of(
                new Player("Vishnu", PlayerType.HUMAN),
                new Player("Avani", PlayerType.AI),
                new Player("VISHNU", PlayerType.AI),
                new Player("Priya", PlayerType.HUMAN)
        );

        PlayerRegistrationResult result = useCase.register(playersToRegister);

        assertFalse(result.success());
        assertEquals(0, result.registeredPlayers().size());
        assertEquals("Duplicate player name 'Vishnu'", result.errors().getFirst());
    }

    @Test
    void shouldAutoGenerateNameForPlayersWithBlankName() {
        List<Player> playersToRegister = List.of(
                new Player("Player_1", PlayerType.AI),
                new Player("", PlayerType.HUMAN),
                new Player("Priya", PlayerType.HUMAN),
                new Player("", PlayerType.AI)
        );

        PlayerRegistrationResult result = useCase.register(playersToRegister);

        assertTrue(result.success());
        List<String> names = result.registeredPlayers().stream()
                .map(Player::name)
                .toList();

        assertEquals(4, names.size());
        assertTrue(names.contains("Player_1"));
        assertTrue(names.contains("Priya"));
        assertEquals(4, names.stream().distinct().count());
        assertTrue(names.stream().anyMatch(name -> name.startsWith("Player_")));
    }


}
