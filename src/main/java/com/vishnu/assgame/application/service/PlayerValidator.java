package com.vishnu.assgame.application.service;

import com.vishnu.assgame.domain.model.Player;

import java.util.List;

public interface PlayerValidator {
    List<String> validate(List<Player> players);
}
