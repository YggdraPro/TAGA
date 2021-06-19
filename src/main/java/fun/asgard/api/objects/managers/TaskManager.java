package fun.asgard.api.objects.managers;

import fun.asgard.api.objects.task.Task;
import fun.asgard.api.objects.task.TaskRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public interface TaskManager {

    void createTask(@NotNull TaskRunnable task, long delay, long period);

    void createTask(@NotNull TaskRunnable task, String taskName, long delay, long period);

    Task getTask(String taskName);

    HashMap<String, Task> getTasks();

}
