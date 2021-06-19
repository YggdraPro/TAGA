package fun.asgard.api.events.gameplayerevents;

import fun.asgard.api.objects.game.Game;

import fun.asgard.api.objects.game.TeamGame;
import org.bukkit.entity.Player;

public class GamePlayerDisconnectEvent extends GamePlayerEvent {

    /**
     *
     * @param game Game
     * @param player Game player
     */
    public GamePlayerDisconnectEvent(Game game, Player player) {
        super(game, player, null);
    }

    /**
     *
     * @param game Game
     * @param player Game player
     */
    public GamePlayerDisconnectEvent(TeamGame game, Player player) {
        super(game, player, null);
    }

}
