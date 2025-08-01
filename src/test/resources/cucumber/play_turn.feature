Feature: Playing a turn in the game

  Players take turns to play one card. The first player can play any card.
  Subsequent players must follow the same suit if possible. If they cannot,
  they must discard another card, and the round ends as a strike.

  Background:
    Given the game has started
    And it is Vishnu's turn
    And the current round suit is Diamond

  Scenario: Player plays a matching suit card
    Given Vishnu has cards: [8♦, Q♠]
    When Vishnu plays 8♦
    Then the card is accepted
    And it is the next player's turn

  Scenario: Player discards when no matching suit
    Given Vishnu has cards: [J♠, Q♣]
    When Vishnu plays Q♣
    Then the card is accepted
    And the round ends due to strike
    And Vishnu is marked as having caused the strike

  Scenario: Player attempts to play wrong card despite having matching suit
    Given Vishnu has cards: [9♦, J♠]
    When Vishnu plays J♠
    Then the move is rejected with message "You must follow the Diamond suit"
    And it is still Vishnu's turn

  Scenario: AI plays valid card (auto-selected)
    Given Arya is an AI player
    And Arya has cards: [3♣, 5♦]
    When it is Arya's turn
    Then Arya plays 5♦
    And it is the next player's turn
