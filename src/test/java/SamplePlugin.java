import fun.asgard.TAGA;
import fun.asgard.api.events.gameevents.GameStartEvent;
import fun.asgard.api.events.gameevents.GameStopEvent;
import fun.asgard.api.objects.game.Game;
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
        game2.setLeaveOnKick(true);
    }

    @Override
    public void onDisable() {
        taga.getGameManager().getGames().forEach((name, game) -> game.shutdown(false));
    }

    @EventHandler
    public void onPlayerJoined(PlayerJoinEvent event) {
        Game game = taga.getGameManager().getGames().get("SampleGame2");
        game.getPlayersManager().connectPlayer(event.getPlayer());
        if (game.getPlayersManager().getPlayers().size() >= 1) {
            game.start();
        }
    }

    @EventHandler
    public void onGameStart(GameStartEvent event) {
        event.getPlayers().forEach(player -> {
            player.getPlayer().sendMessage("Game is started!");
        });
        taga.getTaskManager().createTask(task ->
                event.getPlayers().forEach(player ->
                        player.getPlayer().sendMessage("5 minutes of the game has passed!")),
                event.getGame().getGameName(), 5 * 60 * 1000, 5 * 60 * 1000);
    }

    @EventHandler
    public void onGameStop(GameStopEvent event) {
        event.getPlayers().forEach(player -> {
            player.getPlayer().sendMessage("The game is stopped");
        });
    }

}
