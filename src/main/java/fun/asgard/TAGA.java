package fun.asgard;

import fun.asgard.objects.Game;
import org.bukkit.World;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class TAGA implements Listener {

    private final HashMap<String, Game> games = new HashMap<>();
    private final Plugin plugin;

    public TAGA(Plugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(new fun.asgard.Listener(this), plugin);
    }

    public void createGame(World world, String gameName, long timer) {
        this.addGame(gameName, new Game(this.plugin, world, gameName, timer));
    }

    @Deprecated
    public void addGame(String name, Game game) {
        this.games.put(name, game);
    }

    @Deprecated
    public void addGame(Game game) {
        this.games.put(game.getGameName(), game);
    }

    public HashMap<String, Game> getGames() {
        return this.games;
    }

}
