package fun.asgard.internal.objects.teamgame;


import fun.asgard.TAGA;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

public class ETeamGame extends ATeamGame {

    /**
     * @param client   The one who uses the api
     * @param world    World of the game
     * @param gameName Name of the game
     * @param time     Game time
     */
    public ETeamGame(TAGA taga, Plugin client, World world, String gameName, long time) {
        super(taga, client, world, gameName, time);
    }

}
