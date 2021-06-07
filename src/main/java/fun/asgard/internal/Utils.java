package fun.asgard.internal;

import fun.asgard.api.objects.GamePlayer;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;

public class Utils {

    public static HashSet<GamePlayer> getValuesFromHashMap(HashMap<Player, GamePlayer> map) {
        HashSet<GamePlayer> res = new HashSet<>();
        map.forEach((k, a) -> res.add(a));
        return res;
    }

}
