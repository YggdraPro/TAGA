package fun.asgard.api.objects.game;

import org.bukkit.World;

import java.util.HashSet;

public interface BaseGame {

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


    long getWhenStarted();

    long getTime();

    String getGameName();

    World getWorld();

    void setTime(long time);

    void setLeaveOnKick(boolean kickOnLeave);

    boolean isLeaveOnKick();

    boolean isTeamGame();

}
