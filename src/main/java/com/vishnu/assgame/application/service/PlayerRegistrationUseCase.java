package com.vishnu.assgame.application.service;

import com.vishnu.assgame.application.dto.PlayerRegistrationResult;
import com.vishnu.assgame.domain.model.Player;

import java.util.List;

public class PlayerRegistrationUseCase {

    private final NameGenerationService nameGenerationService;
    private final List<PlayerValidator> validators;

    public PlayerRegistrationUseCase(
            NameGenerationService nameGen,
            List<PlayerValidator> validators) {
        this.nameGenerationService = nameGen;
        this.validators = validators;
    }


    public PlayerRegistrationResult register(List<Player> players) {
        List<Player> resolved = nameGenerationService.assignNames(players);

        List<String> errors = validators.stream()
                .flatMap(validator -> validator.validate(resolved).stream())
                .toList();

        boolean success = errors.isEmpty();
        return new PlayerRegistrationResult(success, success ? resolved : List.of(), errors);
    }
}
