package fun.asgard.api.objects.game;

import org.bukkit.Color;

import java.util.HashSet;

public interface GameTeam {

    HashSet<GamePlayer> getPlayers();

    BaseGame getTeamGame();

    int getScore();

    boolean isLost();

    Color getColor();

    String getName();

    void setScore(int score);

    void setLost(boolean isLost);

    void setColor(Color color);

    void setName(String name);

}
