import fun.asgard.TAGA;
import fun.asgard.api.events.GameStartEvent;
import fun.asgard.api.events.GameStopEvent;
import fun.asgard.api.objects.Game;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class SamplePlugin extends JavaPlugin implements Listener {

    public static TAGA taga;

    @Override
    public void onEnable() {
        taga = new TAGA(this);

        Game game1 = taga.getGameManager().createGame(Bukkit.getWorld("world"), "SampleGame", 5 * 1000);
        Game game2 = taga.getGameManager().createGame(Bukkit.getWorld("world"), "SampleGame2", 5 * 1000);
        game2.setKickOnLeave(true);
    }

    @Override
    public void onDisable() {
        taga.getGameManager().getGames().forEach((name, game) -> game.shutdown(false));
    }

    @EventHandler
    public void onPlayerJoined(PlayerJoinEvent event) {
        Game game = taga.getGameManager().getGames().get("ABOBA");
        game.connectPlayer(event.getPlayer());
        if (game.getPlayers().size() >= 1) {
            game.start();
        }
    }

    @EventHandler
    public void onGameStart(GameStartEvent event) {
        event.getPlayers().forEach(player -> {
            player.getPlayer().sendMessage("Game is started!");
        });
        taga.getTaskManager().createGameTask(() ->
                event.getPlayers().forEach(player ->
                        player.getPlayer().sendMessage("5 minutes of the game has passed!")),
                event.getGame(), 5 * 60 * 1000, 5 * 60 * 1000);
    }

    @EventHandler
    public void onGameStop(GameStopEvent event) {
        event.getPlayers().forEach(player -> {
            player.getPlayer().sendMessage("The game is stopped");
        });
    }

}
