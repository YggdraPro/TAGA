package fun.asgard.api.events;

import fun.asgard.api.objects.Game;

import fun.asgard.api.objects.GamePlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashSet;

public class GameStopEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final Game game;
    private final HashSet<GamePlayer> winners = new HashSet<>();
    private final HashSet<GamePlayer> losers = new HashSet<>();
    private final HashSet<GamePlayer> players = new HashSet<>();

    /**
     *
     * @param game Game
     * @param players Game players
     */
    public GameStopEvent(Game game, GamePlayer[] players) {
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
    public GameStopEvent(Game game, GamePlayer[] players, GamePlayer winner, GamePlayer loser) {
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
    public GameStopEvent(Game game, GamePlayer[] players, GamePlayer[] winners, GamePlayer[] losers) {
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
    public GameStopEvent(Game game, HashSet<GamePlayer> players, HashSet<GamePlayer> winners, HashSet<GamePlayer> losers) {
        this.players.addAll(players);
        this.game = game;

        this.winners.addAll(winners);
        this.losers.addAll(losers);
    }

    public HashSet<GamePlayer> getLosers() {
        return losers;
    }

    public HashSet<GamePlayer> getWinners() {
        return winners;
    }

    public HashSet<GamePlayer> getPlayers() {
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
