package fun.asgard.internal.objects.gameplayer;

import fun.asgard.TAGA;
import fun.asgard.api.objects.game.Game;
import fun.asgard.api.objects.game.GameTeam;

import fun.asgard.api.objects.game.TeamGame;
import org.bukkit.entity.Player;

public class EGamePlayer extends AGamePlayer {

    public EGamePlayer(TAGA taga, Player player, Game game) {
        super(taga, player, game);
    }

    public EGamePlayer(TAGA taga, Player player, TeamGame game, GameTeam team) {
        super(taga, player, game, team);
    }

}
