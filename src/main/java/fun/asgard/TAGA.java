package fun.asgard;

import fun.asgard.api.objects.managers.GamesManager;
import fun.asgard.api.objects.managers.TaskManager;
import fun.asgard.internal.managers.gamemanager.EGameManager;
import fun.asgard.internal.managers.taskmanager.ETaskManager;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public final class TAGA implements Listener {

    private final GamesManager gm;
    private final TaskManager tm;

    private static final String VERSION = "v1.3.0";

    public TAGA (Plugin client) {
        if (client == null)
            throw new IllegalArgumentException("Plugin must be valid");

        client.getServer().getLogger().info("[TAGA] Starting TAGA-" + getVersion() + " on " + client.getServer().getVersion());

        this.gm = new EGameManager(this, client);
        this.tm = new ETaskManager(this, client);
        client.getServer().getPluginManager().registerEvents(new Handler(this, client), client);
    }

    public GamesManager getGameManager() {
        return gm;
    }

    public TaskManager getTaskManager() {
        return tm;
    }

    public static String getVersion() {
        return VERSION;
    }

}
