package fun.asgard.internal.managers.playersmanager;

import fun.asgard.TAGA;
import fun.asgard.api.events.gameplayerevents.GamePlayerConnectEvent;
import fun.asgard.api.events.gameplayerevents.GamePlayerDisconnectEvent;
import fun.asgard.api.events.gameplayerevents.GamePlayerJoinedTeamEvent;
import fun.asgard.api.events.gameteamevents.GameTeamDisconnectEvent;
import fun.asgard.api.objects.game.Game;
import fun.asgard.api.objects.game.GamePlayer;
import fun.asgard.api.objects.game.GameTeam;
import fun.asgard.api.objects.game.TeamGame;
import fun.asgard.api.objects.managers.PlayersManager;
import fun.asgard.internal.objects.gameplayer.EGamePlayer;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.stream.Collectors;

public abstract class APlayersManager implements PlayersManager {

    private final TAGA taga;
    private final Plugin client;
    private final Game game;
    private final TeamGame teamGame;
    private final HashMap<String, GameTeam> teams;

    private final HashMap<Player, GamePlayer> players = new HashMap<>();

    protected APlayersManager(TAGA taga, Plugin client, Game game) {
        this.client = client;
        this.taga = taga;
        this.game = game;
        this.teamGame = null;
        this.teams = null;
    }

    protected APlayersManager(TAGA taga, Plugin client, TeamGame teamGame) {
        this.client = client;
        this.taga = taga;
        this.game = null;
        this.teamGame = teamGame;
        this.teams = new HashMap<>();
    }

    @Override
    public void connectPlayer(Player player) {
        this.checkIfValidGame();
        GamePlayer gp = new EGamePlayer(this.taga, player, getGame());
        this.players.put(player, gp);
        this.client.getServer().getPluginManager().callEvent(new GamePlayerConnectEvent(getGame(), player, gp));
    }

    @Override
    public void connectPlayer(Player player, Location location) {
        this.checkIfValidGame();
        GamePlayer gp = new EGamePlayer(this.taga, player, getGame());
        this.players.put(player, gp);
        player.teleport(location);
        this.client.getServer().getPluginManager().callEvent(new GamePlayerConnectEvent(getGame(), player, gp));
    }

    @Override
    public void connectPlayer(Player player, double x, double y, double z) {
        this.checkIfValidGame();
        GamePlayer gp = new EGamePlayer(this.taga, player, getGame());
        this.players.put(player, gp);
        player.teleport(new Location(getGame().getWorld(), x, y, z));
        this.client.getServer().getPluginManager().callEvent(new GamePlayerConnectEvent(getGame(), player, gp));
    }

    @Override
    public void connectPlayer(Player player, GameTeam team) {
        this.checkIfValidGame(true);
        GamePlayer gp = new EGamePlayer(this.taga, player, getTeamGame(), team);
        this.players.put(player, gp);
        this.client.getServer().getPluginManager().callEvent(new GamePlayerJoinedTeamEvent(getTeamGame(), player, gp));
        this.client.getServer().getPluginManager().callEvent(new GamePlayerConnectEvent(getTeamGame(), player, gp));
    }

    @Override
    public void connectPlayer(Player player, GameTeam team, Location location) {
        this.checkIfValidGame(true);
        GamePlayer gp = new EGamePlayer(this.taga, player, getTeamGame(), team);
        this.players.put(player, gp);
        player.teleport(location);
        this.client.getServer().getPluginManager().callEvent(new GamePlayerJoinedTeamEvent(getTeamGame(), player, gp));
        this.client.getServer().getPluginManager().callEvent(new GamePlayerConnectEvent(getTeamGame(), player, gp));
    }

    @Override
    public void connectPlayer(Player player, GameTeam team, double x, double y, double z) {
        this.checkIfValidGame(true);
        GamePlayer gp = new EGamePlayer(this.taga, player, getTeamGame(), team);
        this.players.put(player, gp);
        player.teleport(new Location(getTeamGame().getWorld(), x, y, z));
        this.client.getServer().getPluginManager().callEvent(new GamePlayerJoinedTeamEvent(getTeamGame(), player, gp));
        this.client.getServer().getPluginManager().callEvent(new GamePlayerConnectEvent(getTeamGame(), player, gp));
    }

    @Override
    public void disconnectPlayer(GamePlayer player) {
        this.checkIfValidGame();
        this.players.remove(player.getPlayer());
        this.client.getServer().getPluginManager().callEvent(new GamePlayerDisconnectEvent(getGame(), player.getPlayer()));
    }

    @Override
    public void disconnectPlayer(GamePlayer player, Location location) {
        this.checkIfValidGame();
        this.players.remove(player.getPlayer());
        player.getPlayer().teleport(location);
        this.client.getServer().getPluginManager().callEvent(new GamePlayerDisconnectEvent(getGame(), player.getPlayer()));
    }

    @Override
    public void disconnectPlayer(GamePlayer player, double x, double y, double z) {
        this.checkIfValidGame();
        this.players.remove(player.getPlayer());
        player.getPlayer().teleport(new Location(getGame().getWorld(), x, y, z));
        this.client.getServer().getPluginManager().callEvent(new GamePlayerDisconnectEvent(getGame(), player.getPlayer()));
    }

    @Override
    public void disconnectTeam(GameTeam team) {
        this.checkIfValidGame(true);
        team.getPlayers().forEach(pl -> this.players.remove(pl.getPlayer()));
        this.teams.remove(team.getName());
        this.client.getServer().getPluginManager().callEvent(new GameTeamDisconnectEvent(getTeamGame(),
                team.getPlayers().stream().map(GamePlayer::getPlayer).collect(Collectors.toSet())));
    }

    @Override
    public void disconnectTeam(GameTeam team, Location location) {
        this.checkIfValidGame(true);
        team.getPlayers().forEach(pl -> {
            this.players.remove(pl.getPlayer());
            pl.getPlayer().teleport(location);
        });
        this.teams.remove(team.getName());
        this.client.getServer().getPluginManager().callEvent(new GameTeamDisconnectEvent(getTeamGame(),
                team.getPlayers().stream().map(GamePlayer::getPlayer).collect(Collectors.toSet())));
    }

    @Override
    public void disconnectTeam(GameTeam team, double x, double y, double z) {
        this.checkIfValidGame(true);
        team.getPlayers().forEach(pl -> {
            this.players.remove(pl.getPlayer());
            pl.getPlayer().teleport(new Location(getGame().getWorld(), x, y, z));
        });
        this.teams.remove(team.getName());
        this.client.getServer().getPluginManager().callEvent(new GameTeamDisconnectEvent(getTeamGame(),
                team.getPlayers().stream().map(GamePlayer::getPlayer).collect(Collectors.toSet())));
    }

    @Override
    public GameTeam createTeam(String name, Color color) {
        this.checkIfValidGame(true);
        return null;
    }

    @Override
    public void sendMessage(String message) {
        this.getPlayers().forEach((player, gamePlayer) -> player.sendMessage(message));
    }

    @Override
    public void sendTeamMessage(String message, GameTeam team) {
        team.getPlayers().forEach(gamePlayer -> gamePlayer.getPlayer().sendMessage(message));
    }

    @Override
    public Game getGame() {
        return game;
    }

    @Override
    public TeamGame getTeamGame() {
        return teamGame;
    }

    @Override
    public HashMap<Player, GamePlayer> getPlayers() {
        return players;
    }

    @Override
    public HashMap<String, GameTeam> getTeams() {
        return teams;
    }

    @Override
    public GameTeam getTeam(String name) {
        return this.getTeams().get(name);
    }

    public boolean isTeamGame() {
        return this.getTeamGame() != null;
    }

    private boolean checkIfValidGame() {
        return this.checkIfValidGame(false);
    }

    private boolean checkIfValidGame(boolean ifTeamInteraction) {
        if (ifTeamInteraction && getTeamGame() == null) {
            throw new IllegalCallerException("It is impossible to call the method, the game is not a team game!");
        } else if (!ifTeamInteraction && getGame() == null) {
            throw new IllegalCallerException("It is impossible to call the method, the game is team!");
        } else {
            return true;
        }
    }

}
