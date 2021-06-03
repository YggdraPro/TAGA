package fun.asgard;

import fun.asgard.events.GamePlayerDeathEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerKickEvent;

public class Handler implements Listener {

    private final TAGA pl;

    Handler (TAGA taga) {
        this.pl = taga;
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent event) {
        this.pl.getGameManager().getGames().forEach((name, game) -> {
            if (game.isLeaveOnKick()
                    && game.getPlayers().contains(event.getPlayer())) {
                game.disconnectPlayer(event.getPlayer());
            }
        });
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        this.pl.getGameManager().getGames().forEach((name, game) -> {
            if (game.getPlayers().contains(event.getEntity())) {
                pl.plugin.getServer().getPluginManager()
                        .callEvent(new GamePlayerDeathEvent(game, event.getEntity()));
            }
        });
    }

}
