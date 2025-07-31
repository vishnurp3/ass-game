package com.vishnu.assgame.application.service;

import com.vishnu.assgame.domain.model.Card;
import com.vishnu.assgame.domain.model.Player;
import com.vishnu.assgame.domain.model.Rank;
import com.vishnu.assgame.domain.model.Suit;

import java.util.List;
import java.util.Map;

public class DetermineStartingPlayerUseCase {

    public Player findStartingPlayer(Map<Player, List<Card>> cardsPerPlayer) {
        for (Map.Entry<Player, List<Card>> entry : cardsPerPlayer.entrySet()) {
            List<Card> cards = entry.getValue();
            boolean hasAceOfSpades = cards.stream()
                    .anyMatch(card -> card.suit() == Suit.SPADE && card.rank() == Rank.ACE);
            if (hasAceOfSpades) {
                return entry.getKey();
            }
        }
        throw new IllegalStateException("No player has the Ace of Spades");
    }
}
