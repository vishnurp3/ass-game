package com.vishnu.assgame.domain.model;

import java.util.Objects;

public record Player(String name, PlayerType type) {
    public Player {
        Objects.requireNonNull(name, "Player name must not be null");
        Objects.requireNonNull(type, "Player type must not be null");
    }
}
