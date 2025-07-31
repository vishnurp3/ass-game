package com.vishnu.assgame.domain.service;

import com.vishnu.assgame.domain.model.Card;
import com.vishnu.assgame.domain.model.Player;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RoundRobinDealingStrategy implements CardDealingStrategy {

    @Override
    public Map<Player, List<Card>> deal(List<Card> deck, List<Player> players) {
        Map<Player, List<Card>> hands = new LinkedHashMap<>();
        for (Player player : players) {
            hands.put(player, new ArrayList<>());
        }
        int index = 0;
        for (Card card : deck) {
            Player currentPlayer = players.get(index % players.size());
            hands.get(currentPlayer).add(card);
            index++;
        }
        return hands;
    }
}
