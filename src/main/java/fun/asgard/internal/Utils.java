package fun.asgard.internal;

import fun.asgard.api.objects.GamePlayer;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;

public class Utils {

    public static <K, V> HashSet<V> getValuesFromHashMap(HashMap<K, V> map) {
        return new HashSet<>(map.values());
    }
    
    public static double getRandom(double min, double max){
        return (Math.random() * ((max - min) + 1)) + min;
    }

}
