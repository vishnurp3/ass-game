package com.vishnu.assgame.application.service;

import com.vishnu.assgame.application.dto.PlayTurnResult;
import com.vishnu.assgame.domain.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PlayTurnUseCaseTest {

    @Test
    void shouldAcceptCardThatMatchesLeadingSuit() {
        Player vishnu = new Player("Vishnu", PlayerType.HUMAN);
        Card eightOfDiamonds = new Card(Suit.DIAMOND, Rank.EIGHT);
        Card queenOfSpades = new Card(Suit.SPADE, Rank.QUEEN);
        List<Card> hand = new ArrayList<>(List.of(eightOfDiamonds, queenOfSpades));
        Suit roundSuit = Suit.DIAMOND;
        Map<Player, List<Card>> hands = Map.of(vishnu, hand);

        PlayTurnUseCase useCase = new PlayTurnUseCase();
        PlayTurnResult result = useCase.playTurn(vishnu, eightOfDiamonds, roundSuit, hands);

        assertTrue(result.success());
        assertEquals(eightOfDiamonds, result.cardPlayed());
        assertFalse(result.isStrike());
        assertEquals(vishnu, result.playedBy());
    }

    @Test
    void shouldAcceptCardWhenNoMatchingSuitAndMarkStrike() {
        Player vishnu = new Player("Vishnu", PlayerType.HUMAN);
        Card jackOfSpades = new Card(Suit.SPADE, Rank.JACK);
        Card queenOfClubs = new Card(Suit.CLUB, Rank.QUEEN);
        List<Card> hand = new ArrayList<>(List.of(jackOfSpades, queenOfClubs));
        Suit roundSuit = Suit.DIAMOND;
        Map<Player, List<Card>> hands = Map.of(vishnu, hand);

        PlayTurnUseCase useCase = new PlayTurnUseCase();
        PlayTurnResult result = useCase.playTurn(vishnu, queenOfClubs, roundSuit, hands);

        assertTrue(result.success());
        assertEquals(queenOfClubs, result.cardPlayed());
        assertTrue(result.isStrike());
        assertEquals(vishnu, result.playedBy());
    }


    @Test
    void shouldRejectCardIfPlayerHasMatchingSuitButPlaysDifferentSuit() {
        Player vishnu = new Player("Vishnu", PlayerType.HUMAN);
        Card nineOfDiamonds = new Card(Suit.DIAMOND, Rank.NINE);
        Card jackOfSpades = new Card(Suit.SPADE, Rank.JACK);
        List<Card> hand = new ArrayList<>(List.of(nineOfDiamonds, jackOfSpades));
        Suit roundSuit = Suit.DIAMOND;
        Map<Player, List<Card>> hands = Map.of(vishnu, hand);

        PlayTurnUseCase useCase = new PlayTurnUseCase();
        PlayTurnResult result = useCase.playTurn(vishnu, jackOfSpades, roundSuit, hands);

        assertFalse(result.success());
        assertNull(result.cardPlayed());
        assertFalse(result.isStrike());
        assertEquals("You must follow the Diamond suit", result.message());
        assertEquals(vishnu, result.playedBy());
    }

}
