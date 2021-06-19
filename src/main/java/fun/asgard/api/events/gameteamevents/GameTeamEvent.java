package fun.asgard.api.events.gameteamevents;

import fun.asgard.api.objects.game.GameTeam;
import fun.asgard.api.objects.game.TeamGame;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class GameTeamEvent extends Event {

    private static HandlerList handlers = null;

    private final Set<Player> players;
    private final TeamGame game;
    private final GameTeam team;

    /**
     *
     * @param game Team game
     * @param players Game players
     */
    protected GameTeamEvent(TeamGame game, Set<Player> players, GameTeam team) {
        handlers = new HandlerList();
        this.players = players;
        this.game = game;
        this.team = team;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public TeamGame getTeamGame() {
        return game;
    }

    public GameTeam getTeam() {
        return team;
    }

    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
