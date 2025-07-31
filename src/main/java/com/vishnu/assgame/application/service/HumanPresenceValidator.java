package com.vishnu.assgame.application.service;

import com.vishnu.assgame.domain.model.Player;
import com.vishnu.assgame.domain.model.PlayerType;

import java.util.List;

public class HumanPresenceValidator implements PlayerValidator {

    @Override
    public List<String> validate(List<Player> players) {
        boolean hasHuman = players.stream()
                .anyMatch(player -> player.type() == PlayerType.HUMAN);

        return hasHuman ? List.of() :
                List.of("At least one human player required");
    }
}
