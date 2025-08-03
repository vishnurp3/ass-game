package com.vishnu.assgame.application.service;

import com.vishnu.assgame.application.dto.PlayTurnResult;
import com.vishnu.assgame.application.dto.RoundResult;
import com.vishnu.assgame.domain.model.Card;
import com.vishnu.assgame.domain.model.Player;
import com.vishnu.assgame.domain.model.Suit;
import com.vishnu.assgame.domain.service.CardProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CompleteRoundUseCase {

    private final PlayTurnUseCase playTurn;
    private final CardProvider cardProvider;

    public CompleteRoundUseCase(PlayTurnUseCase playTurn, CardProvider cardProvider) {
        this.playTurn = playTurn;
        this.cardProvider = cardProvider;
    }

    public RoundResult completeRound(Player starter,
                                     Map<Player, List<Card>> hands,
                                     List<Card> playedHistory) {
        final List<Player> clockwisePlayers = new ArrayList<>(hands.keySet());
        int turnIndex = clockwisePlayers.indexOf(starter);
        final List<Card> roundPile = new ArrayList<>();
        Suit roundSuit = null;
        Card highestRoundCard = null;
        Player nextStarter = null;
        boolean strikeOccurred = false;
        int processedTurns = 0;
        final int totalPlayers = clockwisePlayers.size();
        while (processedTurns < totalPlayers) {
            Player currentPlayer = clockwisePlayers.get(turnIndex);
            List<Card> currentHand = hands.get(currentPlayer);
            Card chosenCard = cardProvider.chooseCard(currentPlayer, roundSuit, currentHand, playedHistory);
            if (roundSuit == null) {
                roundSuit = chosenCard.suit();
            }
            PlayTurnResult turnResult = playTurn.playTurn(currentPlayer, chosenCard, roundSuit, hands);
            if (!turnResult.success()) {
                throw new IllegalStateException("CardProvider supplied illegal card for " + currentPlayer.name());
            }
            roundPile.add(chosenCard);
            playedHistory.add(chosenCard);
            processedTurns++;
            if (turnResult.isStrike()) {
                strikeOccurred = true;
                break;
            }
            if (chosenCard.suit() == roundSuit) {
                if (highestRoundCard == null || chosenCard.rank().ordinal() > highestRoundCard.rank().ordinal()) {
                    highestRoundCard = chosenCard;
                    nextStarter = currentPlayer;
                }
            }
            turnIndex = (turnIndex + 1) % totalPlayers;
        }
        if (nextStarter == null) {
            throw new IllegalStateException("No highest-of-suit card found");
        }
        if (strikeOccurred) {
            hands.get(nextStarter).addAll(roundPile);
        }
        return new RoundResult(strikeOccurred, nextStarter);
    }
}
