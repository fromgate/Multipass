package ru.nukkit.multipass.permissions;

import cn.nukkit.Server;
import ru.nukkit.multipass.WorldParam;
import ru.nukkit.multipass.data.DataProvider;
import ru.nukkit.multipass.event.PermissionsUpdateEvent;
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

    public static void remove(String id) {
        if (groups.containsKey(id)) groups.remove(id);
        saveGroups();
        Users.recalculatePermissions();
    }

    public static Collection<Group> getAll() {
        return groups.values();
    }



    public static boolean addGroup(String id1, WorldParam wp) {
        return (wp.world==null) ? addGroup(id1,wp.param) : addGroup(id1, wp.world, wp.param);
    }

    public static boolean addGroup(String id1, String world, String id2) {
        Group group1 = getGroup(id1);
        Group group2 = getGroup(id2);
        if (group1 == null || group2 == null) return false;
        group1.getWorldPassOrCreate(world).addGroup(group2);
        saveGroups();
        return true;
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
        PermissionsUpdateEvent event = new PermissionsUpdateEvent();
        Server.getInstance().getPluginManager().callEvent(event);
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

    public static boolean removePermission(String id, String permStr) {
        Group group = getGroup(id);
        if (group == null) return false;
        group.removePermission(permStr);
        return true;
    }

    public static boolean removePermission(String id, String world, String permStr) {
        Group group = getGroup(id);
        if (group == null) return false;
        group.removePermission(world, permStr);
        return true;
    }

    public static boolean removePermission(String id, WorldParam wp) {
        return wp.world == null ? removePermission(id, wp.param) : removePermission(id, wp.world, wp.param);
    }
}