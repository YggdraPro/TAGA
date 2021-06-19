package fun.asgard.api.objects.game;

import org.bukkit.entity.Player;

public interface GamePlayer {

    Player getPlayer();

    BaseGame getGame();

    int getScore();

    GameTeam getTeam();

    boolean isDied();

    void setScore(int score);

    void setDied(boolean isDied);

    void setTeam(GameTeam team);

}
