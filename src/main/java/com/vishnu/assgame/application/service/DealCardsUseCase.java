package com.vishnu.assgame.application.service;

import com.vishnu.assgame.domain.model.Card;
import com.vishnu.assgame.domain.model.Deck;
import com.vishnu.assgame.domain.model.Player;
import com.vishnu.assgame.domain.service.CardDealingStrategy;

import java.util.List;
import java.util.Map;

public class DealCardsUseCase {

    private final CardDealingStrategy dealingStrategy;

    public DealCardsUseCase(CardDealingStrategy dealingStrategy) {
        this.dealingStrategy = dealingStrategy;
    }

    public Map<Player, List<Card>> dealCards(List<Player> players) {
        Deck deck = new Deck();
        List<Card> cards = deck.getCards();
        return dealingStrategy.deal(cards, players);
    }
}
