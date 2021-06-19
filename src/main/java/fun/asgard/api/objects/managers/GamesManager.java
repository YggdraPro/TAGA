package fun.asgard.api.objects.managers;

import fun.asgard.api.objects.game.Game;
import fun.asgard.api.objects.game.TeamGame;
import org.bukkit.World;

import java.util.HashMap;

public interface GamesManager {

    Game createGame(World world, String gameName, long timer);

    Game createGame(World world, String gameName);

    TeamGame createTeamGame(World world, String gameName, long timer);

    TeamGame createTeamGame(World world, String gameName);

    Game getGame(String gameName);

    TeamGame getTeamGame(String gameName);

    HashMap<String, Game> getGames();

    HashMap<String, TeamGame> getTeamGames();

}
