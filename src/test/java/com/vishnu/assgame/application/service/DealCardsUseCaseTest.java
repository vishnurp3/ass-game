package com.vishnu.assgame.application.service;

import com.vishnu.assgame.domain.model.Card;
import com.vishnu.assgame.domain.model.Player;
import com.vishnu.assgame.domain.model.PlayerType;
import com.vishnu.assgame.domain.service.RoundRobinDealingStrategy;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DealCardsUseCaseTest {

    @Test
    void shouldDistributeAll52CardsToPlayers() {
        List<Player> players = List.of(
                new Player("Vishnu", PlayerType.HUMAN),
                new Player("Avani", PlayerType.AI),
                new Player("Rahul", PlayerType.HUMAN),
                new Player("Priya", PlayerType.AI),
                new Player("Simran", PlayerType.HUMAN)
        );
        DealCardsUseCase useCase = new DealCardsUseCase(new RoundRobinDealingStrategy());
        Map<Player, List<Card>> result = useCase.dealCards(players);
        int totalCardsDealt = result.values().stream()
                .mapToInt(List::size)
                .sum();
        assertEquals(52, totalCardsDealt, "Total cards dealt should be 52");
    }

    @Test
    void firstPlayersMayReceiveOneExtraCardIfNotDivisible() {
        List<Player> players = List.of(
                new Player("Vishnu", PlayerType.HUMAN),
                new Player("Avani", PlayerType.AI),
                new Player("Rahul", PlayerType.HUMAN),
                new Player("Priya", PlayerType.AI),
                new Player("Simran", PlayerType.HUMAN)
        );
        DealCardsUseCase useCase = new DealCardsUseCase(new RoundRobinDealingStrategy());
        Map<Player, List<Card>> result = useCase.dealCards(players);
        long countOf11 = result.values().stream()
                .filter(hand -> hand.size() == 11)
                .count();
        long countOf10 = result.values().stream()
                .filter(hand -> hand.size() == 10)
                .count();
        assertEquals(2, countOf11, "Exactly 2 players should receive 11 cards");
        assertEquals(3, countOf10, "Remaining 3 players should receive 10 cards");
    }

}
