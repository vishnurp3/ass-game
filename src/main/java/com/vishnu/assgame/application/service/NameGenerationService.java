package com.vishnu.assgame.application.service;

import com.vishnu.assgame.domain.model.Player;

import java.util.List;

public interface NameGenerationService {
    List<Player> assignNames(List<Player> players);
}
