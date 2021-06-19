package fun.asgard.api.events.gameplayerevents;

import fun.asgard.api.objects.game.Game;

import fun.asgard.api.objects.game.GamePlayer;
import fun.asgard.api.objects.game.TeamGame;
import org.bukkit.entity.Player;

public class GamePlayerDeathEvent extends GamePlayerEvent {

    /**
     *
     * @param game Game
     * @param player Game player
     */
    public GamePlayerDeathEvent(Game game, GamePlayer player) {
        super(game, null, player);
    }


    /**
     *
     * @param teamGame Team game
     * @param player Game player
     */
    public GamePlayerDeathEvent(TeamGame teamGame, Player player) {
        super(teamGame, player, null);
    }

}
