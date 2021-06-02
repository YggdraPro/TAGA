package fun.asgard;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerKickEvent;

public class Listener implements org.bukkit.event.Listener {

    private final TAGA pl;

    Listener(TAGA taga) {
        this.pl = taga;
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent event) {
        this.pl.getGames().forEach((name, game) -> {
            if (game.isKickOnLeave()
                    && game.getPlayers().contains(event.getPlayer())) {
                game.disconnectPlayer(event.getPlayer());
            }
        });
    }

}
