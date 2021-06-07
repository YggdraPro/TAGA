package fun.asgard.internal.objects.GameTask;

import fun.asgard.api.objects.Game;
import fun.asgard.api.objects.GameTask;

import java.util.concurrent.ScheduledExecutorService;

public abstract class AGameTask implements GameTask {

    private final ScheduledExecutorService service;
    private final Game game;

    public AGameTask(Game game, ScheduledExecutorService service) {
        this.game = game;
        this.service = service;
    }

    @Override
    public void cancel() {
        this.game.getTasks().remove(this);
        this.service.shutdown();
    }

    @Override
    public Game getGame() {
        return this.game;
    }

}
