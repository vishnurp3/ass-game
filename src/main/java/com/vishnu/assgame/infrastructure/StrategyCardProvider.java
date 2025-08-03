package com.vishnu.assgame.infrastructure;

import com.vishnu.assgame.domain.model.Card;
import com.vishnu.assgame.domain.model.Player;
import com.vishnu.assgame.domain.model.Suit;
import com.vishnu.assgame.domain.service.CardProvider;
import com.vishnu.assgame.domain.service.CardSelectionStrategy;

import java.util.List;

public class StrategyCardProvider implements CardProvider {

    private final CardSelectionStrategy strategy;

    public StrategyCardProvider(CardSelectionStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public Card chooseCard(Player player,
                           Suit roundSuit,
                           List<Card> hand,
                           List<Card> playedHistory) {
        return strategy.selectCard(player, roundSuit, hand, playedHistory)
                .orElseThrow(() -> new IllegalStateException("AI could not choose a card"));
    }
}
