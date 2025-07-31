Feature: Player Registration
  In order to start an “Ass” card game
  As a game setup component
  I need to register between 4 and 8 players
  And ensure each player has a unique name
  And at least one human player is present
  And auto-generate a name if none is provided

  Background:
    Given the game setup has begun

  # Positive scenarios
  Scenario: Register valid 4 players with mix of human and AI
    When the following players are registered:
      | name   | type  |
      | Vishnu | HUMAN |
      | Avani  | AI    |
      | Rahul  | HUMAN |
      | Priya  | AI    |
    Then registration succeeds
    And the game contains 4 players
    And all player names are unique
    And at least one player is human

  Scenario: Register 8 players all with unique names
    When the following players are registered:
      | name    | type  |
      | Alice   | HUMAN |
      | Bob     | HUMAN |
      | Charles | HUMAN |
      | Deepa   | HUMAN |
      | Edward  | AI    |
      | Farah   | AI    |
      | Gautam  | AI    |
      | Heena   | AI    |
    Then registration succeeds
    And the game contains 8 players

  # Negative scenarios
  Scenario: Fail when fewer than 4 players registered
    When the following players are registered:
      | name   | type  |
      | Vishnu | HUMAN |
      | Akash  | AI    |
      | Rahul  | HUMAN |
    Then registration fails with error "Minimum 4 players required"

  Scenario: Fail when more than 8 players registered
    When the following players are registered:
      | name | type  |
      | A    | HUMAN |
      | B    | HUMAN |
      | C    | AI    |
      | D    | AI    |
      | E    | HUMAN |
      | F    | HUMAN |
      | G    | AI    |
      | H    | AI    |
      | I    | HUMAN |
    Then registration fails with error "Maximum 8 players allowed"

  Scenario: Fail when zero human players registered
    When the following players are registered:
      | name | type |
      | AI1  | AI   |
      | AI2  | AI   |
      | AI3  | AI   |
      | AI4  | AI   |
    Then registration fails with error "At least one human player required"

  Scenario: Fail when duplicate names exist
    When the following players are registered:
      | name   | type  |
      | Vishnu | HUMAN |
      | Akash  | AI    |
      | Vishnu | HUMAN |
      | Priya  | AI    |
    Then registration fails with error "Duplicate player name 'Vishnu'"

  # Auto-naming scenarios
  Scenario: Auto-name a player when name is blank
    When the following players are registered:
      | name   | type  |
      | Vishnu | HUMAN |
      |        | AI    |
      |        | HUMAN |
      | Rahul  | AI    |
    Then registration succeeds
    And the game contains 4 players
    And unnamed players receive generated names
    And all player names are unique

  Scenario: Auto-naming avoids collision with provided name
    Given one player is manually named "Echo"
    When the following players are registered:
      | name  | type  |
      | Echo  | HUMAN |
      |       | AI    |
      | Kisah | HUMAN |
      |       | AI    |
    Then registration succeeds
    And no auto-generated name is "Echo"
    And all player names are unique
