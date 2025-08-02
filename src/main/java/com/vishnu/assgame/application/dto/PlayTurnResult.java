package com.vishnu.assgame.application.dto;

import com.vishnu.assgame.domain.model.Card;
import com.vishnu.assgame.domain.model.Player;

public record PlayTurnResult(
        boolean success,
        Card cardPlayed,
        Player playedBy,
        boolean isStrike,
        String message
) {
}