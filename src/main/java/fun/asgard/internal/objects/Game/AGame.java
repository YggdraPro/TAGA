package fun.asgard.internal.objects.Game;

import fun.asgard.TAGA;
import fun.asgard.api.events.GameStartEvent;
import fun.asgard.api.events.GameStopEvent;
import fun.asgard.api.events.PlayerConnectEvent;
import fun.asgard.api.events.PlayerDisconnectEvent;
import fun.asgard.api.objects.Game;
import fun.asgard.api.objects.GamePlayer;
import fun.asgard.api.objects.GameTask;
import fun.asgard.internal.Utils;
import fun.asgard.internal.objects.GamePlayer.EGamePlayer;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.HashSet;

public abstract class AGame implements Game {

    private final World world;
    private final String gameName;
    private final TAGA taga;
    private final Plugin client;

    private final HashMap<Player,GamePlayer> players = new HashMap<>();
    private final HashSet<GameTask> tasks = new HashSet<>();

    private long started;
    private long time;
    private boolean leaveOnKick = false;

    /**
     * @param client   The one who uses the api
     * @param world    World of the game
     * @param gameName Name of the game
     * @param time     Game time
     */
    public AGame(TAGA taga, Plugin client, World world, String gameName, long time) {
        this.taga = taga;
        this.client = client;
        this.gameName = gameName;
        this.world = world;
        this.time = time;
    }

    @Override
    public void start() {
        if (this.world.getLoadedChunks().length <= 0
                || !this.client.getServer().getWorlds().contains(this.world)) {
            this.client.getServer().getWorlds().add(this.world);
        }

        this.started = System.currentTimeMillis();
        Bukkit.getPluginManager().callEvent(new GameStartEvent(this, Utils.getValuesFromHashMap(this.players)));
    }

    @Override
    public void stop(boolean saveWorld) {
        this.client.getServer().getPluginManager().callEvent(new GameStopEvent(this, (GamePlayer[]) Utils.getValuesFromHashMap(this.players).toArray()));
        this.shutdown(saveWorld);
    }


    @Override
    public void stop(GamePlayer winner, GamePlayer loser, boolean saveWorld) {
        this.client.getServer().getPluginManager().callEvent(new GameStopEvent(this, (GamePlayer[]) Utils.getValuesFromHashMap(this.players).toArray(), winner, loser));
        this.shutdown(saveWorld);
    }

    @Override
    public void stop(GamePlayer[] winners, GamePlayer[] losers, boolean saveWorld) {
        this.client.getServer().getPluginManager().callEvent(new GameStopEvent(this, (GamePlayer[]) Utils.getValuesFromHashMap(this.players).toArray(), winners, losers));
        this.shutdown(saveWorld);
    }


    @Override
    public void stop(HashSet<GamePlayer> winners, HashSet<GamePlayer> losers, boolean saveWorld) {
        this.client.getServer().getPluginManager().callEvent(new GameStopEvent(this, Utils.getValuesFromHashMap(this.players), winners, losers));
        this.shutdown(saveWorld);
    }

    @Override
    public void shutdown(boolean saveWorld) {
        this.time = 0;
        this.getTasks().forEach(GameTask::cancel);
        this.players.forEach((pl, gp) -> this.disconnectPlayer(gp));
        Bukkit.unloadWorld(this.world, saveWorld);
    }

    @Override
    public void connectPlayer(Player player) {
        this.players.put(player, new EGamePlayer(this.taga, player, this));
        this.client.getServer().getPluginManager().callEvent(new PlayerConnectEvent(this, player));
    }

    @Override
    public void connectPlayer(Player player, Location location) {
        this.players.put(player, new EGamePlayer(this.taga, player, this));
        player.teleport(location);
        this.client.getServer().getPluginManager().callEvent(new PlayerConnectEvent(this, player));
    }

    @Override
    public void connectPlayer(Player player, double x, double y, double z) {
        this.players.put(player, (new EGamePlayer(this.taga, player, this)));
        player.teleport(new Location(this.world, x, y, z));
        this.client.getServer().getPluginManager().callEvent(new PlayerConnectEvent(this, player));
    }

    @Override
    public void disconnectPlayer(GamePlayer player) {
        this.players.remove(player);
        this.client.getServer().getPluginManager().callEvent(new PlayerDisconnectEvent(this, player.getPlayer()));
    }

    @Override
    public void disconnectPlayer(GamePlayer player, Location location) {
        this.players.remove(player);
        player.getPlayer().teleport(location);
        this.client.getServer().getPluginManager().callEvent(new PlayerDisconnectEvent(this, player.getPlayer()));
    }

    @Override
    public void disconnectPlayer(GamePlayer player, double x, double y, double z) {
        this.players.remove(player);
        player.getPlayer().teleport(new Location(this.world, x, y, z));
        this.client.getServer().getPluginManager().callEvent(new PlayerDisconnectEvent(this, player.getPlayer()));
    }

    @Override
    public HashSet<GameTask> getTasks() {
        return tasks;
    }

    @Override
    public long getWhenStarted() {
        return started;
    }

    @Override
    public HashMap<Player,GamePlayer> getPlayers() {
        return players;
    }

    @Override
    public long getTime() {
        return time;
    }

    @Override
    public String getGameName() {
        return gameName;
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public GameTask getTask() {
        return this.taga.getTaskManager().getTask(this);
    }

    @Override
    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public void setKickOnLeave(boolean kickOnLeave) {
        this.leaveOnKick = kickOnLeave;
    }

    @Override
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
