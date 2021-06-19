package fun.asgard.internal.objects.task;

import fun.asgard.api.objects.task.Task;

import java.util.concurrent.ScheduledExecutorService;

public abstract class ATask implements Task {

    private final ScheduledExecutorService service;
    private String name;

    protected ATask(ScheduledExecutorService service, String name) {
        this.service = service;
        this.name = name;
    }

    @Override
    public void cancel() {
        this.service.shutdown();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

}
