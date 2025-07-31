Feature: Determine Starting Player

  As a game setup component
  I want to find the player who holds the Ace of Spades
  So that the correct player can start the game

  Scenario: Player holding Ace of Spades is identified
    Given the cards have been dealt to the following players:
      | Player | Cards                                                                                                                   |
      | Vishnu | SPADE_A, DIAMOND_2, CLUB_3, HEART_4, SPADE_5, DIAMOND_6, CLUB_7, HEART_8, SPADE_9, DIAMOND_10, CLUB_J, HEART_Q, SPADE_K |
      | Avani  | SPADE_2, DIAMOND_3, CLUB_4, HEART_5, SPADE_6, DIAMOND_7, CLUB_8, HEART_9, SPADE_10, DIAMOND_J, CLUB_Q, HEART_K, SPADE_A |
      | Rahul  | CLUB_2, HEART_3, SPADE_4, DIAMOND_5, CLUB_6, HEART_7, SPADE_8, DIAMOND_9, CLUB_10, HEART_J, SPADE_Q, DIAMOND_K, CLUB_A  |
      | Priya  | DIAMOND_4, CLUB_5, HEART_6, SPADE_7, DIAMOND_8, CLUB_9, HEART_10, SPADE_J, DIAMOND_Q, CLUB_K, HEART_A, SPADE_3, CLUB_J  |
    When the game determines the starting player
    Then the starting player should be "Vishnu"
