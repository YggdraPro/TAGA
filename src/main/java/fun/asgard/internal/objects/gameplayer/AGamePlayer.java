package fun.asgard.internal.objects.gameplayer;

import fun.asgard.TAGA;
import fun.asgard.api.objects.game.Game;
import fun.asgard.api.objects.game.GamePlayer;
import fun.asgard.api.objects.game.GameTeam;

import fun.asgard.api.objects.game.TeamGame;
import org.bukkit.entity.Player;

public abstract class AGamePlayer implements GamePlayer {

    private final TAGA taga;
    private final Player player;
    private final Game game;
    private final TeamGame teamgame;
    private GameTeam team = null;
    private int score = 0;
    private boolean isDied = false;

    protected AGamePlayer(TAGA taga, Player player, Game game) {
        this.taga = taga;
        this.player = player;
        this.game = game;
        this.teamgame = null;
    }

    protected AGamePlayer(TAGA taga, Player player, TeamGame game, GameTeam team) {
        this.taga = taga;
        this.player = player;
        this.teamgame = game;
        this.game = null;
        this.team = team;
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public Game getGame() {
        return this.game;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public GameTeam getTeam() {
        return this.team;
    }

    @Override
    public boolean isDied() {
        return this.isDied;
    }

    @Override
    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public void setDied(boolean isDied) {
        this.isDied = isDied;
    }

    @Override
    public void setTeam(GameTeam team) {
        this.team = team;
    }

}
