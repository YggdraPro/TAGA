package fun.asgard.objects;

import fun.asgard.events.GameStartEvent;
import fun.asgard.events.GameStopEvent;
import fun.asgard.events.PlayerConnectEvent;
import fun.asgard.events.PlayerDisconnectEvent;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Game {

    private final World world;
    private final String gameName;
    private final Plugin plugin;
    private long time;
    private final HashSet<Player> players = new HashSet<>();
    private boolean kickOnLeave = false;

    /**
     *
     * @param world World of the game
     * @param gameName Name of the game
     * @param time Game time
     */
    public Game(Plugin plugin, World world, String gameName, long time) {
        this.plugin = plugin;
        this.gameName = gameName;
        this.world = world;
        this.time = time;
    }

    /**
     * After calling the method, GameStartEvent will be triggered
     */
    public void start() {
        Bukkit.getPluginManager().callEvent(new GameStartEvent(this, this.players.toArray(new Player[0])));
    }

    /**
     * After calling the method, GameEndEvent will be triggered
     */
    public void stop(boolean saveWorld) {
        this.plugin.getServer().getPluginManager().callEvent(new GameStopEvent(this, (Player[]) this.players.toArray()));
        this.shutdown(saveWorld);
    }

    /**
     * After calling the method, GameEndEvent will be triggered
     */
    public void stop(Player winner, Player loser, boolean saveWorld) {
        this.plugin.getServer().getPluginManager().callEvent(new GameStopEvent(this, (Player[]) this.players.toArray(), winner, loser));
        this.shutdown(saveWorld);
    }

    /**
     * After calling the method, GameEndEvent will be triggered
     */
    public void stop(Player[] winners, Player[] losers, boolean saveWorld) {
        this.plugin.getServer().getPluginManager().callEvent(new GameStopEvent(this, (Player[]) this.players.toArray(), winners, losers));
        this.shutdown(saveWorld);
    }

    /**
     * After calling the method, GameEndEvent will be triggered
     */
    public void stop(HashSet<Player> winners, HashSet<Player> losers, boolean saveWorld) {
        this.plugin.getServer().getPluginManager().callEvent(new GameStopEvent(this, this.players, winners, losers));
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

    public void runGameTask(@NotNull Runnable task, long delay, long period) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(task, delay, period, TimeUnit.MILLISECONDS);
    }

    public void connectPlayer(Player player) {
        this.players.add(player);
        this.plugin.getServer().getPluginManager().callEvent(new PlayerConnectEvent(this, player));
    }

    public void disconnectPlayer(Player player) {
        this.players.remove(player);
        this.plugin.getServer().getPluginManager().callEvent(new PlayerDisconnectEvent(this, player));
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

    public void setKickOnLeave(boolean kickOnLeave) {
        this.kickOnLeave = kickOnLeave;
    }

    public boolean isKickOnLeave() {
        return kickOnLeave;
    }

    @Override
    public String toString() {
        return "Game { " +
                "world = " + world.getName() +
                ", gameName = '" + gameName + '\'' +
                ", time = " + time +
                ", players = " + players +
                '}';
    }
}
