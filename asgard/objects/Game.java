package fun.asgard.objects;

import fun.asgard.events.GameStartEvent;
import fun.asgard.events.GameStopEvent;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashSet;

public class Game {

    private final World world;
    private final String gameName;
    private long time;
    private final HashSet<Player> players = new HashSet<>();

    /**
     *
     * @param world World of the game
     * @param gameName Name of the game
     * @param time Game time
     */
    public Game(World world, String gameName, long time) {
        this.gameName = gameName;
        this.world = world;
        this.time = time;
    }

    /**
     * After calling the method, GameStartEvent will be triggered
     * @return Game
     */
    public Game start() {
        Bukkit.getPluginManager().callEvent(new GameStartEvent(this, this.players.toArray(new Player[0])));
        return this;
    }

    /**
     * After calling the method, GameEndEvent will be triggered
     */
    public void stop(boolean saveWorld) {
        new GameStopEvent(this, (Player[]) this.players.toArray());
        this.shutdown(saveWorld);
    }

    /**
     * After calling the method, GameEndEvent will be triggered
     */
    public void stop(Player winner, Player loser, boolean saveWorld) {
        new GameStopEvent(this, (Player[]) this.players.toArray(), winner, loser);
        this.shutdown(saveWorld);
    }

    /**
     * After calling the method, GameEndEvent will be triggered
     */
    public void stop(Player[] winners, Player[] losers, boolean saveWorld) {
        new GameStopEvent(this, (Player[]) this.players.toArray(), winners, losers);
        this.shutdown(saveWorld);
    }

    /**
     * After calling the method, GameEndEvent will be triggered
     */
    public void stop(HashSet<Player> winners, HashSet<Player> losers, boolean saveWorld) {
        new GameStopEvent(this, this.players, winners, losers);
        this.shutdown(saveWorld);
    }

    /**
     * After calling the method, no event will be triggered
     */
    public void shutdown(boolean saveWorld) {
        Bukkit.unloadWorld(this.world, saveWorld);
        this.time = 0;
        this.players.clear();
    }

    public void removePlayer(Player player) {
        this.players.remove(player);
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public HashSet<Player> getPlayers() {
        return players;
    }

    public long getTime() {
        return time;
    }

    public String getGameName() {
        return gameName;
    }

    public World getWorld() {
        return world;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
