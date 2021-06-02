package fun.asgard;

import fun.asgard.objects.Game;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;

public final class TAGA extends JavaPlugin {

    private static final HashSet<Game> games = new HashSet<>();

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    public static void addGame(Game game) {
        games.add(game);
    }

    public static HashSet<Game> getGames() {
        return games;
    }

}
