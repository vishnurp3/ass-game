package com.vishnu.assgame.application.dto;

import com.vishnu.assgame.domain.model.Player;

import java.util.List;

public record PlayerRegistrationResult(
        boolean success,
        List<Player> registeredPlayers,
        List<String> errors) {
}
