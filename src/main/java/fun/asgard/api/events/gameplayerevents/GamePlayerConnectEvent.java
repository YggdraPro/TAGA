package fun.asgard.api.events.gameplayerevents;

import fun.asgard.api.objects.game.Game;

import fun.asgard.api.objects.game.GamePlayer;
import fun.asgard.api.objects.game.TeamGame;
import org.bukkit.entity.Player;

public class GamePlayerConnectEvent extends GamePlayerEvent {

    /**
     *
     * @param game Game
     * @param player Game player
     */
    public GamePlayerConnectEvent(Game game, Player player, GamePlayer gamePlayer) {
        super(game, player, gamePlayer);
    }

    /**
     *
     * @param teamGame Team game
     * @param player Game player
     */
    public GamePlayerConnectEvent(TeamGame teamGame, Player player, GamePlayer gamePlayer) {
        super(teamGame, player, gamePlayer);
    }

}
