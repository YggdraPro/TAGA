package fun.asgard.internal.managers;

import fun.asgard.TAGA;
import fun.asgard.api.objects.Game;
import fun.asgard.internal.objects.Game.EGame;

import org.bukkit.World;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

public class GameManager {

    private final HashMap<String, Game> games = new HashMap<>();
    private final TAGA taga;
    private final Plugin client;

    public GameManager(TAGA taga, Plugin client) {
        this.taga = taga;
        this.client = client;
    }

    public Game createGame(World world, String gameName, long timer) {
        EGame game = new EGame(this.taga, this.client, world, gameName, timer);
        this.games.put(gameName, game);
        return game;
    }

    public Game createGame(World world, String gameName) {
        return this.createGame(world, gameName, -1);
    }

    public Game getGame(String gameName) {
        return this.getGames().get(gameName);
    }

    public HashMap<String, Game> getGames() {
        return this.games;
    }

}
