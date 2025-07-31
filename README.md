# Ass Card Game (Console Edition)

## Overview

This is a console-based Java implementation of the Indian card game "Ass". The objective of the game is for players to discard all their cards by following suit-based rules. The last player left with cards is declared the "Ass" (loser).

The game supports both human and AI players and follows the actual gameplay mechanics commonly used in real-life versions of the game.

---

## Game Rules

### 🎯 Objective

- The goal is to discard all cards in your hand.
- The last remaining player with cards is declared the **Ass**.

### 🎴 Deck and Setup

- A standard 52-card deck is used (no jokers).
- Cards are shuffled and distributed evenly to all players.
- The player holding the **Ace of Spades (♠A)** starts the first round.
- Turns proceed in a **clockwise** direction.

---

## Player Registration

### ✅ Rules

- Minimum players required: **4**
- Maximum players allowed: **8**
- There must be at least **one human player**
- All player names must be:
    - **Non-empty**
    - **Unique**

### 🤖 AI Players

- You can mix human and AI players in any ratio.
- If a name is left blank during registration, the system will automatically assign a unique name.

---

## Gameplay

### 🔁 Turn Mechanics

1. The starting player plays any card.
2. That card's suit becomes the **active suit** for the round.
3. Each subsequent player must:
    - Play a card of the same suit, **if they have one**
    - If not, they must play any other card (this causes a **strike**)

### 🚨 Strike

- A **strike** ends the current round immediately.
- The player who played the **highest card of the active suit** collects all cards from the round into their hand.
- That player starts the next round.

### 🛑 Elimination

- A player who discards all cards is marked as **safe** and does not participate further.
- The last remaining player becomes the **Ass**.

---

## Technical Environment

- **Java Version**: 21
- **Build Tool**: Maven
- **Testing Framework**: JUnit 5
- **BDD Framework**: Cucumber (feature files written in Gherkin)

---

## Getting Started

> _Instructions to run and play the game will be added here._

---

## License

This project is licensed under the MIT License. See `LICENSE` for more details.
