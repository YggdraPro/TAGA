package fun.asgard.internal.managers;

import fun.asgard.TAGA;
import fun.asgard.api.objects.Game;
import fun.asgard.api.objects.GameTask;
import fun.asgard.internal.objects.GameTask.EGameTask;

import org.bukkit.plugin.Plugin;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskManager {

    private final TAGA taga;
    private final Plugin client;
    private final HashMap<Game, GameTask> tasks = new HashMap<>();

    public TaskManager(TAGA taga, Plugin client) {
        this.taga = taga;
        this.client = client;
    }

    public GameTask createGameTask(@NotNull Runnable task, Game game, long delay, long period){
        if (this.tasks.containsKey(game))
            throw new IllegalArgumentException("GameTask has already been created for this game");
        if (delay < 0)
            throw new IllegalArgumentException("Negative delay.");
        if (period <= 0)
            throw new IllegalArgumentException("Non-positive period.");

        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleWithFixedDelay(task, delay, period, TimeUnit.MILLISECONDS);

        GameTask gt = new EGameTask(game, service);
        this.getAllTasks().put(game, gt);
        return gt;
    }

    public GameTask getTask(Game game) {
        return this.getAllTasks().get(game);
    }

    public HashMap<Game, GameTask> getAllTasks() {
        return tasks;
    }

}
