package com.vishnu.assgame.domain.service;

import com.vishnu.assgame.domain.model.Card;
import com.vishnu.assgame.domain.model.Player;
import com.vishnu.assgame.domain.model.Suit;

import java.util.List;

public interface CardProvider {

    Card chooseCard(Player player,
                    Suit roundSuit,
                    List<Card> hand,
                    List<Card> playedHistory);
}
