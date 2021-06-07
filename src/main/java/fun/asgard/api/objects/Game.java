package fun.asgard.api.objects;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;

public interface Game {

    /**
     * After calling the method, GameStartEvent will be triggered
     */
    void start();

    /**
     * After calling the method, GameEndEvent will be triggered
     */
    void stop(boolean saveWorld);

    /**
     * After calling the method, GameEndEvent will be triggered
     */
    void stop(GamePlayer winner, GamePlayer loser, boolean saveWorld);

    /**
     * After calling the method, GameEndEvent will be triggered
     */
    void stop(GamePlayer[] winners, GamePlayer[] losers, boolean saveWorld);

    /**
     * After calling the method, GameEndEvent will be triggered
     */
    void stop(HashSet<GamePlayer> winners, HashSet<GamePlayer> losers, boolean saveWorld);

    /**
     * After calling the method, no event will be triggered
     */
    void shutdown(boolean saveWorld);

    void connectPlayer(Player player);

    void connectPlayer(Player player, Location location);

    void connectPlayer(Player player, double x, double y, double z);

    void disconnectPlayer(GamePlayer player);

    void disconnectPlayer(GamePlayer player, Location location);

    void disconnectPlayer(GamePlayer player, double x, double y, double z);

    HashSet<GameTask> getTasks();

    long getWhenStarted();

    HashMap<Player,GamePlayer> getPlayers();

    long getTime();

    String getGameName();

    World getWorld();

    GameTask getTask();

    void setTime(long time);

    void setKickOnLeave(boolean kickOnLeave);

    boolean isLeaveOnKick();

}
