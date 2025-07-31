Feature: Deal cards to players
  In order to start a fair game
  As the game setup component
  I need to distribute a standard shuffled deck in round-robin fashion
  So that each player receives cards correctly, and if uneven, the earliest players in turn order may receive one extra

  Background:
    Given the game setup has completed registration for 5 players:
      | name   | type  |
      | Vishnu | HUMAN |
      | Avani  | AI    |
      | Rahul  | HUMAN |
      | Priya  | AI    |
      | Simran | HUMAN |

  Scenario: Deal 52 shuffled cards round-robin, distributing extras fairly
    When the deck is shuffled and cards are dealt starting with the first player
    Then total cards dealt should be 52
    And each player should receive at least 10 cards
    And the first two players should receive 11 cards each
    And no card should be duplicated across player hands

  Scenario: Cards are shuffled before dealing (randomness verification)
    When the deck is shuffled before dealing to players
    Then the card order should be different from a sorted deck
