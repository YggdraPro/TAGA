package fun.asgard;

import fun.asgard.objects.Game;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

public class GameManager {

    private final HashMap<String, Game> games = new HashMap<>();
    private final Plugin plugin;

    GameManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public Game createGame(World world, String gameName, long timer) {
        Game game = new Game(this.plugin, world, gameName, timer);
        this.addGame(gameName, game);
        return game;
    }

    public Game createGame(World world, String gameName) {
        return this.createGame(world, gameName, -1);
    }

    public Game getGame(String gameName) {
        return this.getGames().get(gameName);
    }

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
