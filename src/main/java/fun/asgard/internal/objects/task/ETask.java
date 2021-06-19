package fun.asgard.internal.objects.task;

import java.util.concurrent.ScheduledExecutorService;

public class ETask extends ATask {

    public ETask(ScheduledExecutorService service, String name) {
        super(service, name);
    }

}
