package ru.nukkit.multipass.permissions;

import ru.nukkit.multipass.data.DataProvider;
import ru.nukkit.multipass.util.Message;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Igor on 03.05.2016.
 */
public class Groups {

    private static Map<String, Group> groups;

    public static void init() {
        loadGroups();
    }

    public static Group getGroup(String name) {
        return groups.containsKey(name) ? groups.get(name) : null;
    }


    public static boolean exist(String id) {
        return groups.containsKey(id);
    }

    public static void create(String id) {
        if (groups.containsKey(id)) return;
        Group group = new Group(id);
        groups.put(id, group);
        saveGroups();
    }

    public static Collection<Group> getAll() {
        return groups.values();
    }

    public static boolean addGroup(String id1, String id2) {
        Group group1 = getGroup(id1);
        Group group2 = getGroup(id2);
        if (group1 == null || group2 == null) return false;
        group1.addGroup(group2);
        saveGroups();
        return true;
    }

    public static boolean setPerm(String id, String permStr) {
        Group group = getGroup(id);
        Message.debugMessage(group.getPermissions().size());
        if (group == null) return false;
        group.setPermission(permStr);
        saveGroups();
        return true;
    }

    public static boolean setGroup(String id1, String id2) {
        Group group1 = getGroup(id1);
        Group group2 = getGroup(id2);
        if (group1 == null || group2 == null) return false;
        group1.setGroup(group2);
        saveGroups();
        return true;
    }

    public static boolean isPermissionSet(String id, String permStr) {
        Group group = getGroup(id);
        if (group == null) return false;
        return group.isPermissionSet(permStr);
    }

    public static boolean setPrefix(String id, String prefix) {
        Group group = getGroup(id);
        if (group == null) return false;
        group.setPrefix(prefix);
        saveGroups();
        return true;
    }

    public static boolean setSuffix(String id, String suffix) {
        Group group = getGroup(id);
        if (group == null) return false;
        group.setSuffix(suffix);
        saveGroups();
        return true;
    }

    public static void saveGroups() {
        Message.debugMessage("Saving groups");
        DataProvider.saveGroups();
        Users.recalculatePermissions();
    }

    public static void loadGroups() {
        Message.debugMessage("Loading groups");
        groups = new TreeMap<String, Group>(String.CASE_INSENSITIVE_ORDER);
        groups.putAll(DataProvider.loadGroups());
        Users.recalculatePermissions();
    }
}