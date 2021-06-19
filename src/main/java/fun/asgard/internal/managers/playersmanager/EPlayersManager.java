package fun.asgard.internal.managers.playersmanager;

import fun.asgard.TAGA;
import fun.asgard.api.objects.game.Game;
import fun.asgard.api.objects.game.TeamGame;
import org.bukkit.plugin.Plugin;

public class EPlayersManager extends APlayersManager {

    public EPlayersManager(TAGA taga, Plugin client, Game game) {
        super(taga, client, game);
    }

    public EPlayersManager(TAGA taga, Plugin client, TeamGame teamGame) {
        super(taga, client, teamGame);
    }

}
