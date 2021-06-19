package fun.asgard.api.objects.managers;

import fun.asgard.api.objects.game.Game;
import fun.asgard.api.objects.game.GamePlayer;
import fun.asgard.api.objects.game.GameTeam;
import fun.asgard.api.objects.game.TeamGame;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;

public interface PlayersManager {

    Game getGame();

    TeamGame getTeamGame();

    HashMap<String, GameTeam> getTeams();

    HashMap<Player,GamePlayer> getPlayers();

    GameTeam getTeam(String name);

    void connectPlayer(Player player);

    void connectPlayer(Player player, Location location);

    void connectPlayer(Player player, double x, double y, double z);

    void disconnectPlayer(GamePlayer player);

    void disconnectPlayer(GamePlayer player, Location location);

    void disconnectPlayer(GamePlayer player, double x, double y, double z);

    GameTeam createTeam(String name, Color color);

    void connectPlayer(Player player, GameTeam team);

    void connectPlayer(Player player, GameTeam team, Location location);

    void connectPlayer(Player player, GameTeam team, double x, double y, double z);

    void disconnectTeam(GameTeam team);

    void disconnectTeam(GameTeam team, Location location);

    void disconnectTeam(GameTeam team, double x, double y, double z);

    void sendMessage(String message);

    void sendTeamMessage(String message, GameTeam team);

}
