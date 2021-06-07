package fun.asgard.internal.objects.GameTask;

import fun.asgard.api.objects.Game;

import java.util.concurrent.ScheduledExecutorService;

public class EGameTask extends AGameTask {

    public EGameTask(Game game, ScheduledExecutorService service) {
        super(game, service);
    }

}
