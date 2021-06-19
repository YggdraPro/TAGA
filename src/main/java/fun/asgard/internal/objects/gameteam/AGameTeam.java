package fun.asgard.internal.objects.gameteam;

import fun.asgard.api.objects.game.GamePlayer;
import fun.asgard.api.objects.game.GameTeam;
import fun.asgard.api.objects.game.TeamGame;
import org.bukkit.Color;

import java.util.HashSet;

public abstract class AGameTeam implements GameTeam {

    private final HashSet<GamePlayer> players = new HashSet<>();
    private final TeamGame game;

    private int score;
    private boolean isLost;
    private Color color;
    private String name;

    protected AGameTeam(TeamGame game, Color color, String name) {
        this.game = game;
        this.color = color;
        this.name = name;
    }

    @Override
    public HashSet<GamePlayer> getPlayers() {
        return this.players;
    }

    @Override
    public TeamGame getTeamGame() {
        return this.game;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public boolean isLost() {
        return this.isLost;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public void setLost(boolean isLost) {
        this.isLost = isLost;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

}
