import fun.asgard.TAGA;
import fun.asgard.events.GameStartEvent;
import fun.asgard.events.GameStopEvent;
import fun.asgard.objects.Game;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class SamplePlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Game game = new Game(Bukkit.getWorld("world"), "SampleGame", 5 * 60 * 20);
        TAGA.addGame(game);

        Game game2 = new Game(Bukkit.getWorld("world"), "SampleGame2", 5 * 60 * 20);
        TAGA.addGame("ABOBA", game);
    }

    @Override
    public void onDisable() {
        TAGA.getGames().forEach((name, game) -> game.shutdown(false));
    }

    @EventHandler
    public void onPlayerJoined(PlayerJoinEvent event) {
        Game game = TAGA.getGames().get("ABOBA");
        game.addPlayer(event.getPlayer());
        if (game.getPlayers().size() >= 1) {
            game.start();
        }
    }

    @EventHandler
    public void onGameStart(GameStartEvent event) {
        event.getPlayers().forEach(player -> {
            player.sendMessage("Game is started!");
        });
    }

    @EventHandler
    public void onGameStop(GameStopEvent event) {
        event.getPlayers().forEach(player -> {
            player.sendMessage("The game is stopped");
        });
    }

}
