package ru.nukkit.multipass.util;

import java.util.Collection;

/**
 * Created by Igor on 06.05.2016.
 */
public class StringUtil {

    public static String join(Collection<String> collection) {
        return join(collection, ", ");
    }

    public static String join(Collection<String> collection, String divider) {
        if (divider == null) divider = " ";
        StringBuilder sb = new StringBuilder();
        for (String s : collection) {
            if (sb.length() > 0) sb.append(divider);
            sb.append(s);
        }
        return sb.toString();
    }
}