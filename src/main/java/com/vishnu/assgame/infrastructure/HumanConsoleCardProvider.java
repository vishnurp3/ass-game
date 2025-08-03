package com.vishnu.assgame.infrastructure;

import com.vishnu.assgame.domain.model.Card;
import com.vishnu.assgame.domain.model.Player;
import com.vishnu.assgame.domain.model.Suit;
import com.vishnu.assgame.domain.service.CardProvider;

import java.util.List;

public class HumanConsoleCardProvider implements CardProvider {

    @Override
    public Card chooseCard(Player player, Suit roundSuit, List<Card> hand, List<Card> playedHistory) {
        return null;
    }
}
