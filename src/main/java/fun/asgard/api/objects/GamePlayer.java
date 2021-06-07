package fun.asgard.api.objects;

import org.bukkit.entity.Player;

public interface GamePlayer {

    Player getPlayer();

    Game getGame();

    int getScore();

    boolean isDied();

    void setScore(int score);

    void setDied(boolean isDied);

}
