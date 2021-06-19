package fun.asgard.api.events.gameevents;

import fun.asgard.api.objects.game.Game;

import fun.asgard.api.objects.game.GamePlayer;
import fun.asgard.api.objects.game.TeamGame;

import java.util.Arrays;
import java.util.HashSet;

public class GameStartEvent extends GameEvent {

    /**
     *
     * @param game Game
     * @param players Game players
     */
    public GameStartEvent(Game game, GamePlayer[] players) {
        super(game, null, null, new HashSet<>(Arrays.asList(players)));
    }

    /**
     *
     * @param game Game
     * @param players Game players
     */
    public GameStartEvent(Game game, HashSet<GamePlayer> players) {
        super(game, null, null, players);
    }

    /**
     *
     * @param game Game
     * @param players Game players
     */
    public GameStartEvent(TeamGame game, GamePlayer[] players) {
        super(game, null, null, new HashSet<>(Arrays.asList(players)));
    }

    /**
     *
     * @param game Game
     * @param players Game players
     */
    public GameStartEvent(TeamGame game, HashSet<GamePlayer> players) {
        super(game, null, null, players);
    }

}
