package fun.asgard;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public final class TAGA implements Listener {

    private final GameManager gm;
    private static final String VERSION = "v1.1.0";
    protected final Plugin plugin;

    public TAGA (Plugin plugin) {
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin must be valid");
        }
        this.plugin = plugin;

        plugin.getServer().getLogger().info("Starting TAGA-" + getVersion() + " on " + plugin.getServer().getVersion());

        this.gm = new GameManager(plugin);
        plugin.getServer().getPluginManager().registerEvents(new Handler(this), plugin);
    }

    public GameManager getGameManager() {
        return gm;
    }

    public static String getVersion() {
        return VERSION;
    }

}
