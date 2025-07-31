package com.vishnu.assgame.application.service;

import com.vishnu.assgame.domain.model.Player;

import java.util.List;

public class TotalCountValidator implements PlayerValidator {

    private static final int MIN_PLAYERS = 4;
    private static final int MAX_PLAYERS = 8;

    @Override
    public List<String> validate(List<Player> players) {
        if (players.size() < MIN_PLAYERS) {
            return List.of("Minimum 4 players required");
        }

        if (players.size() > MAX_PLAYERS) {
            return List.of("Maximum 8 players allowed");
        }

        return List.of();
    }
}
