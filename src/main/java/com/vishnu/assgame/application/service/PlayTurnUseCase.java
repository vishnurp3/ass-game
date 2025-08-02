package com.vishnu.assgame.application.service;

import com.vishnu.assgame.application.dto.PlayTurnResult;
import com.vishnu.assgame.domain.model.Card;
import com.vishnu.assgame.domain.model.Player;
import com.vishnu.assgame.domain.model.Suit;

import java.util.List;
import java.util.Map;

public class PlayTurnUseCase {

    public PlayTurnResult playTurn(Player player, Card cardToPlay, Suit roundSuit, Map<Player, List<Card>> hands) {
        List<Card> playerHand = hands.get(player);
        boolean hasMatchingSuit = playerHand.stream()
                .anyMatch(card -> card.suit() == roundSuit);
        if (hasMatchingSuit && cardToPlay.suit() != roundSuit) {
            return new PlayTurnResult(false, null, player, false, "You must follow the " + roundSuit.displayName() + " suit");
        }
        playerHand.remove(cardToPlay);
        boolean isStrike = !hasMatchingSuit;
        return new PlayTurnResult(true, cardToPlay, player, isStrike, null);
    }
}
