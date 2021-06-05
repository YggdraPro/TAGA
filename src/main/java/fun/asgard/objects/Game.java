package fun.asgard.objects;

import fun.asgard.events.GameStartEvent;
import fun.asgard.events.GameStopEvent;
import fun.asgard.events.PlayerConnectEvent;
import fun.asgard.events.PlayerDisconnectEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Game {

    private final World world;
    private final String gameName;
    private final Plugin plugin;

    private final HashSet<Player> players = new HashSet<>();
    private final HashSet<Timer> tasks = new HashSet<>();

    private long started;
    private long time;
    private boolean leaveOnKick = false;

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
        if (this.world.getLoadedChunks().length <= 0
                || !this.plugin.getServer().getWorlds().contains(this.world)) {
            this.plugin.getServer().getWorlds().add(this.world);
        }

        this.started = System.currentTimeMillis();
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
        this.time = 0;
        this.getTasks().forEach(Timer::cancel);
        this.players.forEach(this::disconnectPlayer);
        Bukkit.unloadWorld(this.world, saveWorld);
    }

    public void runGameTask(@NotNull Runnable task, long delay, long period) {
        if (delay < 0)
            throw new IllegalArgumentException("Negative delay.");
        if (period <= 0)
            throw new IllegalArgumentException("Non-positive period.");

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                task.run();
            }
        }, delay, period);

        this.getTasks().add(timer);
    }

    public void connectPlayer(Player player) {
        this.players.add(player);
        this.plugin.getServer().getPluginManager().callEvent(new PlayerConnectEvent(this, player));
    }

    public void connectPlayer(Player player, Location location) {
        this.players.add(player);
        player.teleport(location);
        this.plugin.getServer().getPluginManager().callEvent(new PlayerConnectEvent(this, player));
    }

    public void connectPlayer(Player player, double x, double y, double z) {
        this.players.add(player);
        player.teleport(new Location(this.world, x, y, z));
        this.plugin.getServer().getPluginManager().callEvent(new PlayerConnectEvent(this, player));
    }

    public void disconnectPlayer(Player player) {
        this.players.remove(player);
        this.plugin.getServer().getPluginManager().callEvent(new PlayerDisconnectEvent(this, player));
    }

    public void disconnectPlayer(Player player, Location location) {
        this.players.remove(player);
        player.teleport(location);
        this.plugin.getServer().getPluginManager().callEvent(new PlayerDisconnectEvent(this, player));
    }

    public void disconnectPlayer(Player player, double x, double y, double z) {
        this.players.remove(player);
        player.teleport(new Location(this.world, x, y, z));
        this.plugin.getServer().getPluginManager().callEvent(new PlayerDisconnectEvent(this, player));
    }

    public HashSet<Timer> getTasks() {
        return tasks;
    }

    public long getWhenStarted() {
        return started;
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
        this.leaveOnKick = kickOnLeave;
    }

    public boolean isLeaveOnKick() {
        return leaveOnKick;
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
