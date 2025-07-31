package com.vishnu.assgame.domain.service;

import com.vishnu.assgame.domain.model.Card;
import com.vishnu.assgame.domain.model.Player;

import java.util.List;
import java.util.Map;

public interface CardDealingStrategy {
    Map<Player, List<Card>> deal(List<Card> deck, List<Player> players);
}
