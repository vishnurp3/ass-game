package com.vishnu.assgame.application.dto;

import com.vishnu.assgame.domain.model.Player;

public record RoundResult(boolean strikeOccurred, Player nextStarter) {
}
