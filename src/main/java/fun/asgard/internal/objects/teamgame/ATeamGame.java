package fun.asgard.internal.objects.teamgame;

import fun.asgard.TAGA;
import fun.asgard.api.events.gameevents.GameStartEvent;
import fun.asgard.api.events.gameevents.GameStopEvent;
import fun.asgard.api.objects.game.GamePlayer;
import fun.asgard.api.objects.game.GameTeam;
import fun.asgard.api.objects.game.TeamGame;
import fun.asgard.api.objects.managers.PlayersManager;
import fun.asgard.internal.Utils;
import fun.asgard.internal.managers.playersmanager.EPlayersManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.HashSet;

public abstract class ATeamGame implements TeamGame {

    private final PlayersManager playersManager;
    private final World world;
    private final String gameName;
    private final TAGA taga;
    private final Plugin client;

    private long started;
    private long time;
    private boolean leaveOnKick = false;

    /**
     * @param client   The one who uses the api
     * @param world    World of the game
     * @param gameName Name of the game
     * @param time     Game time
     */
    protected ATeamGame(TAGA taga, Plugin client, World world, String gameName, long time) {
        this.taga = taga;
        this.client = client;
        this.gameName = gameName;
        this.world = world;
        this.time = time;
        this.playersManager = new EPlayersManager(taga, client, this);
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
    public long getWhenStarted() {
        return this.started;
    }

    @Override
    public long getTime() {
        return this.time;
    }

    @Override
    public String getGameName() {
        return this.gameName;
    }

    @Override
    public World getWorld() {
        return this.world;
    }

    @Override
    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public void setLeaveOnKick(boolean leaveOnKick) {
        this.leaveOnKick = leaveOnKick;
    }

    @Override
    public boolean isLeaveOnKick() {
        return this.leaveOnKick;
    }

    @Override
    public boolean isTeamGame() {
        return true;
    }

    @Override
    public String toString() {
        return "TeamGame { " +
                "world = " + world.getName() +
                ", gameName = '" + gameName + '\'' +
                ", time = " + time +
                '}';
    }

}
