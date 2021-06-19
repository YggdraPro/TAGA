package fun.asgard.internal;

import java.util.HashMap;
import java.util.HashSet;

public class Utils {

    public static <K, V> HashSet<V> valueSet(HashMap<K, V> map) {
        return new HashSet<>(map.values());
    }

    public static double getRandom(double min, double max){
        return (Math.random() * ((max - min) + 1)) + min;
    }

}
