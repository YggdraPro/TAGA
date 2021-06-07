package fun.asgard;

import fun.asgard.internal.managers.GameManager;
import fun.asgard.internal.managers.TaskManager;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public final class TAGA implements Listener {

    private final GameManager gm;
    private final TaskManager tm;

    private static final String VERSION = "v1.2.0";

    public TAGA (Plugin client) {
        if (client == null)
            throw new IllegalArgumentException("Plugin must be valid");

        client.getServer().getLogger().info("[TAGA] Starting TAGA-" + getVersion() + " on " + client.getServer().getVersion());

        this.gm = new GameManager(this, client);
        this.tm = new TaskManager(this, client);
        client.getServer().getPluginManager().registerEvents(new Handler(this, client), client);
    }

    public GameManager getGameManager() {
        return gm;
    }

    public TaskManager getTaskManager() {
        return tm;
    }

    public static String getVersion() {
        return VERSION;
    }

}
