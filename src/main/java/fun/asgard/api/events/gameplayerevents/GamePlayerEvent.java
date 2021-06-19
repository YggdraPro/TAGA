package fun.asgard.api.events.gameplayerevents;

import fun.asgard.api.objects.game.Game;
import fun.asgard.api.objects.game.GamePlayer;
import fun.asgard.api.objects.game.TeamGame;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class GamePlayerEvent extends Event {

    private static HandlerList handlers = null;

    private final Player player;
    private final GamePlayer gp;
    private final TeamGame teamGame;
    private final Game game;

    /**
     *
     * @param game Game
     * @param player Game player
     */
    protected GamePlayerEvent(Game game, Player player, GamePlayer gp) {
        handlers = new HandlerList();
        this.gp = gp;
        this.player = player;
        this.game = game;
        this.teamGame = null;
    }

    /**
     *
     * @param teamGame Game
     * @param player Game player
     */
    protected GamePlayerEvent(TeamGame teamGame, Player player, GamePlayer gp) {
        handlers = new HandlerList();
        this.gp = gp;
        this.player = player;
        this.teamGame = teamGame;
        this.game = null;
    }

    public GamePlayer getGamePlayer() {
        return gp;
    }

    public Player getPlayer() {
        return player;
    }

    public TeamGame getTeamGame() {
        return teamGame;
    }

    public Game getGame() {
        return game;
    }

    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}