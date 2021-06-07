package fun.asgard;

import fun.asgard.api.events.GamePlayerDeathEvent;
import fun.asgard.api.objects.GamePlayer;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.plugin.Plugin;

public class Handler implements Listener {

    private final TAGA pl;
    private final Plugin client;

    public Handler(TAGA taga, Plugin client) {
        this.pl = taga;
        this.client = client;
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent event) {
        this.pl.getGameManager().getGames().forEach((name, game) -> {
            if (game.isLeaveOnKick()
                    && game.getPlayers().containsKey(event.getPlayer())) {
                game.disconnectPlayer(game.getPlayers().get(event.getPlayer()));
            }
        });
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        this.pl.getGameManager().getGames().forEach((name, game) -> {
            if (game.getPlayers().containsKey(event.getEntity())) {
                GamePlayer gp = game.getPlayers().get(event.getEntity());
                gp.setDied(true);
                this.client.getServer().getPluginManager()
                        .callEvent(new GamePlayerDeathEvent(game, gp));
            }
        });
    }

}
