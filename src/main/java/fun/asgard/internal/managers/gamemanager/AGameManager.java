package fun.asgard.internal.managers.gamemanager;

import fun.asgard.TAGA;
import fun.asgard.api.objects.game.Game;
import fun.asgard.api.objects.game.TeamGame;
import fun.asgard.api.objects.managers.GamesManager;
import fun.asgard.internal.objects.game.EGame;

import fun.asgard.internal.objects.teamgame.ETeamGame;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

public abstract class AGameManager implements GamesManager {

    private final HashMap<String, Game> games = new HashMap<>();
    private final HashMap<String, TeamGame> teamGames = new HashMap<>();
    private final TAGA taga;
    private final Plugin client;

    protected AGameManager(TAGA taga, Plugin client) {
        this.taga = taga;
        this.client = client;
    }

    @Override
    public Game createGame(World world, String gameName, long timer) {
        EGame game = new EGame(this.taga, this.client, world, gameName, timer);
        this.games.put(gameName, game);
        return game;
    }

    @Override
    public Game createGame(World world, String gameName) {
        return this.createGame(world, gameName, -1);
    }

    @Override
    public TeamGame createTeamGame(World world, String gameName, long timer) {
        TeamGame game = new ETeamGame(this.taga, this.client, world, gameName, timer);
        this.teamGames.put(gameName, game);
        return game;
    }

    @Override
    public TeamGame createTeamGame(World world, String gameName) {
        return this.createTeamGame(world, gameName, -1);
    }

    @Override
    public Game getGame(String gameName) {
        return this.getGames().get(gameName);
    }

    @Override
    public TeamGame getTeamGame(String gameName) {
        return this.getTeamGames().get(gameName);
    }

    @Override
    public HashMap<String, Game> getGames() {
        return this.games;
    }

    @Override
    public HashMap<String, TeamGame> getTeamGames() {
        return this.teamGames;
    }
}
