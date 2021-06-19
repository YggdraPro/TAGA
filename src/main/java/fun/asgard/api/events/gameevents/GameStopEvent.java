package fun.asgard.api.events.gameevents;

import fun.asgard.api.objects.game.Game;

import fun.asgard.api.objects.game.GamePlayer;
import fun.asgard.api.objects.game.TeamGame;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class GameStopEvent extends GameEvent {

    /**
     *
     * @param game Game
     * @param players Game players
     */
    public GameStopEvent(Game game, GamePlayer[] players) {
        super(game, null, null, new HashSet<>(Arrays.asList(players)));
    }

    /**
     *
     * @param game Game
     * @param players Game players
     * @param winner Winner of the game
     * @param loser Loser of the game
     */
    public GameStopEvent(Game game, GamePlayer[] players, GamePlayer winner, GamePlayer loser) {
        super(game, new HashSet<>(Collections.singletonList(winner)), new HashSet<>(Collections.singletonList(loser)),
                new HashSet<>(Arrays.asList(players)));
    }

    /**
     *
     * @param game Game
     * @param players Game players
     * @param winners Winners of the game
     * @param losers Losers of the game
     */
    public GameStopEvent(Game game, GamePlayer[] players, GamePlayer[] winners, GamePlayer[] losers) {
        super(game, new HashSet<>(Arrays.asList(winners)), new HashSet<>(Arrays.asList(losers)),
                new HashSet<>(Arrays.asList(players)));
    }

    /**
     *
     * @param game Game
     * @param players Game players
     * @param winners Winners of the game
     * @param losers Losers of the game
     */
    public GameStopEvent(Game game, HashSet<GamePlayer> players, HashSet<GamePlayer> winners, HashSet<GamePlayer> losers) {
        super(game, winners, losers, players);
    }

    /**
     *
     * @param game Game
     * @param players Game players
     */
    public GameStopEvent(TeamGame game, GamePlayer[] players) {
        super(game, null, null, new HashSet<>(Arrays.asList(players)));
    }

    /**
     *
     * @param game Game
     * @param players Game players
     * @param winner Winner of the game
     * @param loser Loser of the game
     */
    public GameStopEvent(TeamGame game, GamePlayer[] players, GamePlayer winner, GamePlayer loser) {
        super(game, new HashSet<>(Collections.singletonList(winner)), new HashSet<>(Collections.singletonList(loser)),
                new HashSet<>(Arrays.asList(players)));
    }

    /**
     *
     * @param game Game
     * @param players Game players
     * @param winners Winners of the game
     * @param losers Losers of the game
     */
    public GameStopEvent(TeamGame game, GamePlayer[] players, GamePlayer[] winners, GamePlayer[] losers) {
        super(game, new HashSet<>(Arrays.asList(winners)), new HashSet<>(Arrays.asList(losers)),
                new HashSet<>(Arrays.asList(players)));
    }

    /**
     *
     * @param game Game
     * @param players Game players
     * @param winners Winners of the game
     * @param losers Losers of the game
     */
    public GameStopEvent(TeamGame game, HashSet<GamePlayer> players, HashSet<GamePlayer> winners, HashSet<GamePlayer> losers) {
        super(game, winners, losers, players);
    }

}
