package fun.asgard.events;

import fun.asgard.objects.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashSet;

public class GameStopEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final Game game;
    private final HashSet<Player> winners = new HashSet<>();
    private final HashSet<Player> losers = new HashSet<>();
    private final HashSet<Player> players = new HashSet<>();

    /**
     *
     * @param game Game
     * @param players Game players
     */
    public GameStopEvent(Game game, Player[] players) {
        this.players.addAll(Arrays.asList(players));
        this.game = game;
    }

    /**
     *
     * @param game Game
     * @param players Game players
     * @param winner Winner of the game
     * @param loser Loser of the game
     */
    public GameStopEvent(Game game, Player[] players, Player winner, Player loser) {
        this.players.addAll(Arrays.asList(players));
        this.game = game;
        this.winners.add(winner);
        this.losers.add(loser);
    }

    /**
     *
     * @param game Game
     * @param players Game players
     * @param winners Winners of the game
     * @param losers Losers of the game
     */
    public GameStopEvent(Game game, Player[] players, Player[] winners, Player[] losers) {
        this.players.addAll(Arrays.asList(players));
        this.game = game;

        this.winners.addAll(Arrays.asList(winners));
        this.losers.addAll(Arrays.asList(losers));
    }

    /**
     *
     * @param game Game
     * @param players Game players
     * @param winners Winners of the game
     * @param losers Losers of the game
     */
    public GameStopEvent(Game game, HashSet<Player> players, HashSet<Player> winners, HashSet<Player> losers) {
        this.players.addAll(players);
        this.game = game;

        this.winners.addAll(winners);
        this.losers.addAll(losers);
    }

    public HashSet<Player> getLosers() {
        return losers;
    }

    public HashSet<Player> getWinners() {
        return winners;
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
