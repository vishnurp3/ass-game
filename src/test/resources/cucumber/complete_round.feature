Feature: Complete a round

  A round begins with a starting player. The first card sets the round suit.
  Players continue clockwise. If a player cannot follow that suit they may
  discard any card and the round ends immediately as a strike.
  • If there is **no strike**, all cards played in the round are discarded and
  the player who played the highest card of the round suit starts the next round.
  • If there **is** a strike, the same highest-of-suit player collects the pile
  and starts the next round.

  Background:
    Given 4 players are seated clockwise as: Vishnu, Arya, Rahul, Priya

  Scenario: All players follow suit; pile is discarded, highest card starts next round
    Given the hands for this round are:
      | Player | Cards |
      | Vishnu | 4♠    |
      | Arya   | 6♠    |
      | Rahul  | 7♠    |
      | Priya  | 9♠    |
    And Vishnu starts the round
    When players play in order:
      | Player | Card |
      | Vishnu | 4♠   |
      | Arya   | 6♠   |
      | Rahul  | 7♠   |
      | Priya  | 9♠   |
    Then the round ends without strike
    And the pile is discarded
    And Priya starts the next round

  Scenario: A strike occurs; highest of round suit collects pile
    Given the hands for this round are:
      | Player | Cards |
      | Vishnu | 4♦    |
      | Arya   | 6♦    |
      | Rahul  | J♣    |
      | Priya  | 9♦    |
    And Vishnu starts the round
    When players play in order:
      | Player | Card |
      | Vishnu | 4♦   |
      | Arya   | 6♦   |
      | Rahul  | J♣   |
    Then the round ends due to strike
    And Arya collects the pile
    And Arya starts the next round
