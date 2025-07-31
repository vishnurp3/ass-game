package com.vishnu.assgame.application.service;

import com.vishnu.assgame.domain.model.Player;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DuplicateNameValidator implements PlayerValidator {

    @Override
    public List<String> validate(List<Player> players) {
        Map<String, Long> nameCounts = players.stream()
                .map(player -> player.name().trim().toLowerCase())
                .collect(Collectors.groupingBy(name -> name, Collectors.counting()));

        Optional<String> duplicate = nameCounts.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .findFirst();

        return duplicate
                .map(name -> {
                    String displayName = name.substring(0, 1).toUpperCase() + name.substring(1);
                    return List.of("Duplicate player name '" + displayName + "'");
                })
                .orElse(List.of());
    }
}
