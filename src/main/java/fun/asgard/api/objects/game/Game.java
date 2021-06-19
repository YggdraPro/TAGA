package fun.asgard.api.objects.game;

import fun.asgard.api.objects.managers.PlayersManager;
import java.util.HashSet;

public interface Game extends BaseGame {

    void start();

    void stop(boolean saveWorld);

    void stop(GamePlayer winner, GamePlayer loser, boolean saveWorld);

    void stop(GamePlayer[] winners, GamePlayer[] losers, boolean saveWorld);

    void stop(HashSet<GamePlayer> winners, HashSet<GamePlayer> losers, boolean saveWorld);

    void shutdown(boolean saveWorld);

    PlayersManager getPlayersManager();

}
