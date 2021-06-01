package fun.asgard.events;

import fun.asgard.objects.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashSet;

public class GameStartEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final HashSet<Player> players = new HashSet<>();
    private final Game game;

    /**
     *
     * @param game Game
     * @param players Game players
     */
    public GameStartEvent(Game game, Player[] players) {
        this.players.addAll(Arrays.asList(players));
        this.game = game;
    }

    /**
     *
     * @param game Game
     * @param players Game players
     */
    public GameStartEvent(Game game, HashSet<Player> players) {
        this.players.addAll(players);
        this.game = game;
    }

    public HashSet<Player> getPlayers() {
        return players;
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
