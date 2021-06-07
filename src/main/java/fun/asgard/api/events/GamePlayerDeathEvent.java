package fun.asgard.api.events;

import fun.asgard.api.objects.Game;

import fun.asgard.api.objects.GamePlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import org.jetbrains.annotations.NotNull;

public class GamePlayerDeathEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final GamePlayer player;
    private final Game game;

    /**
     *
     * @param game Game
     * @param player Game player
     */
    public GamePlayerDeathEvent(Game game, GamePlayer player) {
        this.player = player;
        this.game = game;
    }

    public GamePlayer getPlayer() {
        return player;
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
