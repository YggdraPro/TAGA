package fun.asgard.api.events.gameteamevents;

import fun.asgard.api.objects.game.TeamGame;
import org.bukkit.entity.Player;

import java.util.Set;

public class GameTeamDisconnectEvent extends GameTeamEvent {

    /**
     *
     * @param game Game
     * @param players Game players
     */
    public GameTeamDisconnectEvent(TeamGame game, Set<Player> players) {
        super(game, players, null);
    }

}