package fun.asgard.api.events.gameplayerevents;

import fun.asgard.api.objects.game.GamePlayer;
import fun.asgard.api.objects.game.TeamGame;
import org.bukkit.entity.Player;

public class GamePlayerLeftTeamEvent extends GamePlayerEvent {

    public GamePlayerLeftTeamEvent(TeamGame teamGame, Player player, GamePlayer gp) {
        super(teamGame, player, gp);
    }

}
