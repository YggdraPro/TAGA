package fun.asgard.events;

import fun.asgard.objects.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class GamePlayerDeathEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final Player player;
    private final Game game;

    /**
     *
     * @param game Game
     * @param player Game player
     */
    public GamePlayerDeathEvent(Game game, Player player) {
        this.player = player;
        this.game = game;
    }

    public Player getPlayer() {
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
