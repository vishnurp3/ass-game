package com.vishnu.assgame.application.service;

import com.vishnu.assgame.application.dto.RoundResult;
import com.vishnu.assgame.domain.model.*;
import com.vishnu.assgame.domain.service.CardProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class CompleteRoundUseCaseTest {

    private CardProvider cardProvider;
    private CompleteRoundUseCase roundUseCase;

    private final Player vishnu = new Player("Vishnu", PlayerType.HUMAN);
    private final Player arya = new Player("Arya", PlayerType.HUMAN);
    private final Player rahul = new Player("Rahul", PlayerType.HUMAN);
    private final Player priya = new Player("Priya", PlayerType.HUMAN);

    @BeforeEach
    void setUp() {
        PlayTurnUseCase playTurnUseCase = new PlayTurnUseCase();
        cardProvider = Mockito.mock(CardProvider.class);
        roundUseCase = new CompleteRoundUseCase(playTurnUseCase, cardProvider);
    }

    @Test
    void shouldFinishRoundWithoutStrikeAndChooseHighestCardStarter() {
        Map<Player, List<Card>> hands = new LinkedHashMap<>();
        Card c1 = new Card(Suit.SPADE, Rank.FOUR);
        Card c2 = new Card(Suit.SPADE, Rank.SIX);
        Card c3 = new Card(Suit.SPADE, Rank.SEVEN);
        Card c4 = new Card(Suit.SPADE, Rank.NINE);

        hands.put(vishnu, new ArrayList<>(List.of(c1)));
        hands.put(arya, new ArrayList<>(List.of(c2)));
        hands.put(rahul, new ArrayList<>(List.of(c3)));
        hands.put(priya, new ArrayList<>(List.of(c4)));

        when(cardProvider.chooseCard(eq(vishnu), any(), eq(hands.get(vishnu)), anyList())).thenReturn(c1);
        when(cardProvider.chooseCard(eq(arya), any(), eq(hands.get(arya)), anyList())).thenReturn(c2);
        when(cardProvider.chooseCard(eq(rahul), any(), eq(hands.get(rahul)), anyList())).thenReturn(c3);
        when(cardProvider.chooseCard(eq(priya), any(), eq(hands.get(priya)), anyList())).thenReturn(c4);

        RoundResult result = roundUseCase.completeRound(vishnu, hands, new ArrayList<>());

        assertFalse(result.strikeOccurred(), "No strike expected");
        assertEquals(priya, result.nextStarter(), "Highest 9♠ should start next round");
        assertEquals(0, hands.get(vishnu).size());
        assertEquals(0, hands.get(arya).size());
        assertEquals(0, hands.get(rahul).size());
        assertEquals(0, hands.get(priya).size());
    }

    @Test
    void strikeEndsRoundAndHighestSuitPlayerCollectsPile() {
        Map<Player, List<Card>> hands = new LinkedHashMap<>();
        Card c1 = new Card(Suit.DIAMOND, Rank.FOUR);
        Card c2 = new Card(Suit.DIAMOND, Rank.SIX);
        Card c3 = new Card(Suit.CLUB, Rank.JACK);
        Card c4 = new Card(Suit.DIAMOND, Rank.NINE);

        hands.put(vishnu, new ArrayList<>(List.of(c1)));
        hands.put(arya, new ArrayList<>(List.of(c2)));
        hands.put(rahul, new ArrayList<>(List.of(c3)));
        hands.put(priya, new ArrayList<>(List.of(c4)));

        when(cardProvider.chooseCard(eq(vishnu), any(), eq(hands.get(vishnu)), anyList())).thenReturn(c1);
        when(cardProvider.chooseCard(eq(arya), any(), eq(hands.get(arya)), anyList())).thenReturn(c2);
        when(cardProvider.chooseCard(eq(rahul), any(), eq(hands.get(rahul)), anyList())).thenReturn(c3);

        RoundResult rr = roundUseCase.completeRound(vishnu, hands, new ArrayList<>());

        assertTrue(rr.strikeOccurred());
        assertEquals(arya, rr.nextStarter());
        assertEquals(0, hands.get(vishnu).size());
        assertEquals(0, hands.get(rahul).size());
        assertEquals(3, hands.get(arya).size());
    }
}
