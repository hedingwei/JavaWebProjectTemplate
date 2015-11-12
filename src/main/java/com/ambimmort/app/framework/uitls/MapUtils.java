package com.ambimmort.app.framework.uitls;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;

/**
 * Created by ShiYun on 2015/7/8 0008.
 */
public class MapUtils {

    /**
     * @param h
     * @return 实现对map按照value降序排序
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static Map.Entry[] getSortedHashtableByValueDown(Map h) {
        Set set = h.entrySet();
        Map.Entry[] entries = (Map.Entry[]) set.toArray(new Map.Entry[set.size()]);
        Arrays.sort(entries, new Comparator() {
            public int compare(Object arg0, Object arg1) {
                Long key1 = Long.valueOf(((Map.Entry) arg0).getValue().toString());
                Long key2 = Long.valueOf(((Map.Entry) arg1).getValue().toString());
                return key2.compareTo(key1);
            }
        });
        return entries;
    }

    /**
     * @param h
     * @return 实现对map按照value升序排序
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static Map.Entry[] getSortedHashtableByValueUP(Map h) {
        Set set = h.entrySet();
        Map.Entry[] entries = (Map.Entry[]) set.toArray(new Map.Entry[set.size()]);
        Arrays.sort(entries, new Comparator() {
            public int compare(Object arg0, Object arg1) {
                Long key1 = Long.valueOf(((Map.Entry) arg0).getValue().toString());
                Long key2 = Long.valueOf(((Map.Entry) arg1).getValue().toString());
                return key1.compareTo(key2);
            }
        });
        return entries;
    }

}
