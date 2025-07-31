package com.vishnu.assgame.infrastructure;

import com.vishnu.assgame.application.service.NameGenerationService;
import com.vishnu.assgame.domain.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DefaultNameGenerationService implements NameGenerationService {

    private static final String DEFAULT_NAME_PREFIX = "Player_";

    @Override
    public List<Player> assignNames(List<Player> players) {
        Set<String> usedNames = players.stream()
                .map(player -> player.name().trim())
                .filter(name -> !name.isBlank())
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        int counter = 1;
        List<Player> resolved = new ArrayList<>();

        for (Player player : players) {
            String name = player.name().trim();
            if (!name.isBlank()) {
                resolved.add(player);
                continue;
            }

            String autoName;
            do {
                autoName = DEFAULT_NAME_PREFIX + counter++;
            } while (usedNames.contains(autoName.toLowerCase()));

            usedNames.add(autoName.toLowerCase());
            resolved.add(new Player(autoName, player.type()));
        }

        return resolved;
    }
}
