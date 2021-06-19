package fun.asgard.api.events.gameevents;

import fun.asgard.api.objects.game.Game;
import fun.asgard.api.objects.game.GamePlayer;
import fun.asgard.api.objects.game.TeamGame;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

public class GameEvent extends Event {

    private static HandlerList handlers = null;

    private final TeamGame teamGame;
    private final Game game;
    private final HashSet<GamePlayer> winners = new HashSet<>();
    private final HashSet<GamePlayer> losers = new HashSet<>();
    private final HashSet<GamePlayer> players = new HashSet<>();

    /**
     *
     * @param game Game
     */
    protected GameEvent(Game game, HashSet<GamePlayer> winners, HashSet<GamePlayer> losers, HashSet<GamePlayer> players) {
        handlers = new HandlerList();
        this.game = game;
        this.teamGame = null;
    }

    /**
     *
     * @param teamGame Game
     */
    protected GameEvent(TeamGame teamGame, HashSet<GamePlayer> winners, HashSet<GamePlayer> losers, HashSet<GamePlayer> players) {
        handlers = new HandlerList();
        this.teamGame = teamGame;
        this.game = null;
    }

    public HashSet<GamePlayer> getLosers() {
        return losers;
    }

    public HashSet<GamePlayer> getPlayers() {
        return players;
    }

    public HashSet<GamePlayer> getWinners() {
        return winners;
    }

    public TeamGame getTeamGame() {
        return teamGame;
    }

    public Game getGame() {
        return game;
    }

    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}