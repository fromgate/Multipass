package ru.nukkit.multipass.util;

import ru.nukkit.multipass.permissions.BasePass;

import java.util.Comparator;

/**
 * Created by Igor on 16.05.2016.
 */
public class HighBase implements Comparator<BasePass> {
    @Override
    public int compare(BasePass o1, BasePass o2) {
        if (o1.getPriority()==o2.getPriority()) return 0;
        return o1.getPriority()>o2.getPriority() ? 1 : -1;
    }
}
