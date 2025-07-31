package com.vishnu.assgame.application.service;

import com.vishnu.assgame.domain.model.Card;
import com.vishnu.assgame.domain.model.Player;
import com.vishnu.assgame.domain.model.PlayerType;
import com.vishnu.assgame.domain.model.Rank;
import com.vishnu.assgame.domain.model.Suit;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DetermineStartingPlayerUseCaseTest {

    @Test
    void shouldReturnPlayerWhoHasAceOfSpades() {
        Player vishnu = new Player("Vishnu", PlayerType.HUMAN);
        Player avani = new Player("Avani", PlayerType.AI);
        Player rahul = new Player("Rahul", PlayerType.AI);
        Player priya = new Player("Priya", PlayerType.AI);
        Map<Player, List<Card>> cardsPerPlayer = Map.of(
                vishnu, List.of(new Card(Suit.SPADE, Rank.ACE), new Card(Suit.HEART, Rank.TWO)),
                avani, List.of(new Card(Suit.HEART, Rank.THREE)),
                rahul, List.of(new Card(Suit.DIAMOND, Rank.FOUR)),
                priya, List.of(new Card(Suit.CLUB, Rank.FIVE))
        );
        var useCase = new DetermineStartingPlayerUseCase();
        Player startingPlayer = useCase.findStartingPlayer(cardsPerPlayer);
        assertEquals("Vishnu", startingPlayer.name());
    }

}
