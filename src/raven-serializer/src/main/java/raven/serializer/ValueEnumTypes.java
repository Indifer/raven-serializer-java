package raven.serializer;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author yi.liang
 * @since JDK1.8
 * created by 2018/1/9 23:00:00
 */
public class ValueEnumTypes {

    private static HashMap<String, HashMap<Integer, ValueEnumType>> cache = new HashMap<>();


    public static <T extends ValueEnumType> HashMap<Integer, ValueEnumType> getValues(Class<T> clazz) throws Exception {

        String key = clazz.getName();
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        Method method = clazz.getMethod("values");
        ValueEnumType[] inter = (ValueEnumType[]) method.invoke(null, null);
        HashMap<Integer, ValueEnumType> map = new HashMap<>(inter.length);
        for (int i = 0; i < inter.length; i++) {
            ValueEnumType valueEnumType = inter[i];
            map.put(valueEnumType.getValue(), valueEnumType);
        }

        synchronized (cache) {
            if (!cache.containsKey(key)) {
                cache.put(key, map);
            }
        }
        return map;
    }

    public static <T extends ValueEnumType> T valueOf(Class<T> clazz, int value) {
        try {
            HashMap<Integer, ValueEnumType> map = getValues(clazz);
            if (map.containsKey(value)) {
                return (T)map.get(value);
            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

}