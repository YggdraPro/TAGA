package fun.asgard.internal.objects.gameteam;

import fun.asgard.api.objects.game.TeamGame;
import org.bukkit.Color;

public class EGameTeam extends AGameTeam {

    /**
     *
     * @param teamGame Team game
     * @param color Color of the team
     * @param name Team name
     */
    public EGameTeam(TeamGame teamGame, Color color, String name) {
        super(teamGame, color, name);
    }

}
