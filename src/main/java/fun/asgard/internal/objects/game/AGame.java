package fun.asgard.internal.objects.game;

import fun.asgard.TAGA;
import fun.asgard.api.events.gameevents.GameStartEvent;
import fun.asgard.api.events.gameevents.GameStopEvent;
import fun.asgard.api.objects.game.Game;
import fun.asgard.api.objects.game.GamePlayer;
import fun.asgard.api.objects.managers.PlayersManager;
import fun.asgard.internal.Utils;
import fun.asgard.internal.managers.playersmanager.EPlayersManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

import java.util.HashSet;

public abstract class AGame implements Game {

    private final World world;
    private final String gameName;
    private final TAGA taga;
    private final Plugin client;
    private final PlayersManager playersManager;

    private long started;
    private long time;
    private boolean leaveOnKick = false;

    /**
     * @param client   The one who uses the api
     * @param world    World of the game
     * @param gameName Name of the game
     * @param time     Game time
     */
    protected AGame(TAGA taga, Plugin client, World world, String gameName, long time) {
        this.taga = taga;
        this.client = client;
        this.gameName = gameName;
        this.world = world;
        this.time = time;
        this.playersManager = new EPlayersManager(taga, client, this);
    }

    @Override
    public long getWhenStarted() {
        return started;
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
    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public void setLeaveOnKick(boolean kickOnLeave) {
        this.leaveOnKick = kickOnLeave;
    }

    @Override
    public boolean isLeaveOnKick() {
        return leaveOnKick;
    }

    @Override
    public void start() {
        if (this.world.getLoadedChunks().length <= 0
                || !this.client.getServer().getWorlds().contains(this.world)) {
            this.client.getServer().getWorlds().add(this.world);
        }

        this.started = System.currentTimeMillis();
        Bukkit.getPluginManager().callEvent(new GameStartEvent(this, Utils.valueSet(this.getPlayersManager().getPlayers())));

    }

    @Override
    public void stop(boolean saveWorld) {
        this.client.getServer().getPluginManager().callEvent(new GameStopEvent(this, (GamePlayer[]) Utils.valueSet(this.getPlayersManager().getPlayers()).toArray()));
        this.shutdown(saveWorld);
    }

    @Override
    public void stop(GamePlayer winner, GamePlayer loser, boolean saveWorld) {
        this.client.getServer().getPluginManager().callEvent(new GameStopEvent(this, (GamePlayer[]) Utils.valueSet(this.getPlayersManager().getPlayers()).toArray(), winner, loser));
        this.shutdown(saveWorld);
    }

    @Override
    public void stop(GamePlayer[] winners, GamePlayer[] losers, boolean saveWorld) {
        this.client.getServer().getPluginManager().callEvent(new GameStopEvent(this, (GamePlayer[]) Utils.valueSet(this.getPlayersManager().getPlayers()).toArray(), winners, losers));
        this.shutdown(saveWorld);
    }


    @Override
    public void stop(HashSet<GamePlayer> winners, HashSet<GamePlayer> losers, boolean saveWorld) {
        this.client.getServer().getPluginManager().callEvent(new GameStopEvent(this, Utils.valueSet(this.getPlayersManager().getPlayers()), winners, losers));
        this.shutdown(saveWorld);
    }

    @Override
    public void shutdown(boolean saveWorld) {
        this.time = 0;
        this.getPlayersManager().getPlayers().forEach((pl, gp) -> this.getPlayersManager().disconnectPlayer(gp));
        Bukkit.unloadWorld(this.world, saveWorld);
    }

    @Override
    public PlayersManager getPlayersManager() {
        return this.playersManager;
    }

    @Override
    public boolean isTeamGame() {
        return false;
    }

    @Override
    public String toString() {
        return "Game { " +
                "world = " + world.getName() +
                ", gameName = '" + gameName + '\'' +
                ", time = " + time +
                '}';
    }

}
