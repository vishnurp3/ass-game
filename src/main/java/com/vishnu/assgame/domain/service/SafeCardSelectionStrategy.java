package com.vishnu.assgame.domain.service;

import com.vishnu.assgame.domain.model.Card;
import com.vishnu.assgame.domain.model.Player;
import com.vishnu.assgame.domain.model.Suit;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SafeCardSelectionStrategy implements CardSelectionStrategy {

    @Override
    public Optional<Card> selectCard(Player player, Suit roundSuit, List<Card> hand, List<Card> playedHistory) {
        return hand.stream()
                .filter(card -> card.suit() == roundSuit)
                .min(Comparator.comparingInt(card -> card.rank().ordinal()))
                .or(() -> hand.stream()
                        .max(Comparator.comparingInt(card -> card.rank().ordinal())));
    }
}
