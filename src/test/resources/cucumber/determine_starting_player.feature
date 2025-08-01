Feature: Determine Starting Player

  As a game setup component
  I want to find the player who holds the Ace of Spades
  So that the correct player can start the game

  Scenario: Player holding Ace of Spades is identified
    Given the cards have been dealt to the following players:
      | Player | Cards                                               |
      | Vishnu | A♠, 2♦, 3♣, 4♥, 5♠, 6♦, 7♣, 8♥, 9♠, 10♦, J♣, Q♥, K♠ |
      | Avani  | 2♠, 3♦, 4♣, 5♥, 6♠, 7♦, 8♣, 9♥, 10♠, J♦, Q♣, K♥, A♦ |
      | Rahul  | 2♣, 3♥, 4♠, 5♦, 6♣, 7♥, 8♠, 9♦, 10♣, J♥, Q♠, K♦, A♣ |
      | Priya  | 4♦, 5♣, 6♥, 7♠, 8♦, 9♣, 10♥, J♠, Q♦, K♣, A♥, 3♠, J♣ |
    When the game determines the starting player
    Then the starting player should be "Vishnu"
