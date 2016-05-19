package ru.nukkit.multipass.util;

import ru.nukkit.multipass.permissions.Group;

import java.util.Comparator;

/**
 * Created by Igor on 16.05.2016.
 */
public class LowGroup implements Comparator<Group> {
    @Override
    public int compare(Group o1, Group o2) {
        if (o1.getPriority()==o2.getPriority()) return 0;
        return o1.getPriority()<o2.getPriority() ? 1 : -1;
    }
}
