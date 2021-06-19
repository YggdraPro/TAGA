package fun.asgard.internal.managers.taskmanager;

import fun.asgard.TAGA;
import fun.asgard.api.objects.managers.TaskManager;
import fun.asgard.api.objects.task.Task;
import fun.asgard.api.objects.task.TaskRunnable;
import fun.asgard.internal.Utils;
import fun.asgard.internal.objects.task.ETask;

import org.bukkit.plugin.Plugin;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class ATaskManager implements TaskManager {

    private final TAGA taga;
    private final Plugin client;
    private final HashMap<String, Task> tasks = new HashMap<>();

    protected ATaskManager(TAGA taga, Plugin client) {
        this.taga = taga;
        this.client = client;
    }

    @Override
    public void createTask(@NotNull TaskRunnable task, long delay, long period){
        if (delay < 0)
            throw new IllegalArgumentException("Negative delay.");
        if (period <= 0)
            throw new IllegalArgumentException("Non-positive period.");

        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        String id = "Task-" + this.getRandomId();
        Task gt = new ETask(service, id);
        service.scheduleWithFixedDelay(() -> task.execute(gt), delay, period, TimeUnit.MILLISECONDS);

        this.tasks.put(id, gt);
    }

    @Override
    public void createTask(@NotNull TaskRunnable task, String taskName, long delay, long period){
        if (delay < 0)
            throw new IllegalArgumentException("Negative delay.");
        if (period <= 0)
            throw new IllegalArgumentException("Non-positive period.");

        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        Task gt = new ETask(service, taskName);
        service.scheduleWithFixedDelay(() -> task.execute(gt), delay, period, TimeUnit.MILLISECONDS);

        this.tasks.put(taskName, gt);
    }

    @Override
    public Task getTask(String taskName) {
        return this.getTasks().get(taskName);
    }

    @Override
    public HashMap<String, Task> getTasks() {
        return tasks;
    }

    private String getRandomId() {
        String str = String.valueOf(Utils.getRandom(this.getTasks().size(), 1000));
        if (this.getTask(str) != null) return getRandomId();
        else return str;
    }

}
