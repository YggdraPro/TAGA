package fun.asgard.internal.objects.game;

import fun.asgard.TAGA;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

public class EGame extends AGame {

    /**
     * @param client   The one who uses the api
     * @param world    World of the game
     * @param gameName Name of the game
     * @param time     Game time
     */
    public EGame(TAGA taga, Plugin client, World world, String gameName, long time) {
        super(taga, client, world, gameName, time);
    }

}
