package com.vishnu.assgame.application.factory;

import com.vishnu.assgame.domain.model.Player;
import com.vishnu.assgame.domain.model.PlayerType;
import com.vishnu.assgame.domain.service.CardProvider;
import com.vishnu.assgame.domain.service.CardSelectionStrategy;
import com.vishnu.assgame.infrastructure.HumanConsoleCardProvider;
import com.vishnu.assgame.infrastructure.StrategyCardProvider;

public class CardProviderFactory {

    private final CardProvider humanProvider;
    private final CardProvider aiProvider;

    public CardProviderFactory(CardSelectionStrategy defaultStrategy) {
        this.humanProvider = new HumanConsoleCardProvider();
        this.aiProvider = new StrategyCardProvider(defaultStrategy);
    }

    public CardProvider forPlayer(Player player) {
        return player.type() == PlayerType.HUMAN ? humanProvider : aiProvider;
    }
}
