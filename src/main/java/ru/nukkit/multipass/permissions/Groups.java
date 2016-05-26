/*
    Multipass, Nukkit permissions plugin
    Copyright (C) 2016  fromgate, nukkit.ru

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package ru.nukkit.multipass.permissions;

import cn.nukkit.Server;
import ru.nukkit.multipass.MultipassPlugin;
import ru.nukkit.multipass.data.DataProvider;
import ru.nukkit.multipass.event.PermissionsUpdateEvent;
import ru.nukkit.multipass.util.Message;
import ru.nukkit.multipass.util.WorldParam;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

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

    public static boolean create(String id) {
        return create(id, -1);
    }

    public static boolean create(String id, int priority) {
        if (id == null || id.isEmpty() || groups.containsKey(id)) return false;
        Group group = priority < 0 ? new Group(id) : new Group(id, priority);
        groups.put(id, group);
        saveGroups();
        return true;
    }

    public static boolean remove(String id) {
        if (!groups.containsKey(id)) return false;
        groups.remove(id);
        saveGroups();
        Users.recalculatePermissions();
        return true;
    }

    public static Collection<Group> getAll() {
        return groups.values();
    }


    public static boolean addGroup(String groupId, WorldParam wp) {
        return wp.hasWorld() ? addGroup(groupId, wp.world, wp.param) : addGroup(groupId, wp.param);
    }

    public static boolean addGroup(String groupId1, String world, String groupId2) {
        Group group1 = getGroup(groupId1);
        Group group2 = getGroup(groupId2);
        if (group1 == null || group2 == null) return false;
        group1.getWorldPassOrCreate(world).addGroup(group2);
        saveGroups();
        return true;
    }

    public static boolean addGroup(String groupId1, String groupId2) {
        Group group1 = getGroup(groupId1);
        Group group2 = getGroup(groupId2);
        if (group1 == null || group2 == null) return false;
        group1.addGroup(group2);
        saveGroups();
        return true;
    }

    public static boolean setPerm(String id, WorldParam wp) {
        return wp.hasWorld() ? setPerm(id, wp.world, wp.param) : setPerm(id, wp.param);
    }

    private static boolean setPerm(String id, String world, String perm) {
        Group group = getGroup(id);
        if (group == null) return false;
        group.setPermission(world, perm);
        saveGroups();
        return true;
    }

    public static boolean setPerm(String id, String permStr) {
        Group group = getGroup(id);
        if (group == null) return false;
        group.setPermission(permStr);
        saveGroups();
        return true;
    }

    public static boolean setGroup(String id1, WorldParam wp) {
        return wp.hasWorld() ? setGroup(id1, wp.world, wp.param) : setGroup(id1, wp.param);
    }

    public static boolean setGroup(String id1, String world, String param) {
        Group group1 = getGroup(id1);
        Group group2 = getGroup(param);
        if (group1 == null || group2 == null) return false;
        group1.getWorldPassOrCreate(world).setGroup(group2);
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
        if (!exist(MultipassPlugin.getCfg().defaultGroup))
            Groups.create(MultipassPlugin.getCfg().defaultGroup);
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
        return wp.hasWorld() ? removePermission(id, wp.world, wp.param) : removePermission(id, wp.param);
    }

    public static boolean isDefault(String groupStr) {
        if (!groups.containsKey(groupStr)) return false;
        return groups.get(groupStr).isDefault();
    }
}