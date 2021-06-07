package fun.asgard.internal.objects.GamePlayer;

import fun.asgard.TAGA;
import fun.asgard.api.objects.Game;
import fun.asgard.api.objects.GamePlayer;

import org.bukkit.entity.Player;

public abstract class AGamePlayer implements GamePlayer {

    private final TAGA taga;
    private final Player player;
    private final Game game;
    private int score = 0;
    private boolean isDied = false;

    public AGamePlayer(TAGA taga, Player player, Game game) {
        this.taga = taga;
        this.player = player;
        this.game = game;
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

}
