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

package ru.nukkit.multipass;

import cn.nukkit.Player;
import cn.nukkit.Server;
import ru.nukkit.multipass.permissions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static ru.nukkit.multipass.permissions.Users.getUser;

public class Multipass {

    /**
     * Get active groups of Player (groups from inactive worlds will be excluded)
     *
     * @param player
     * @return
     */
    public static List<String> getGroups(Player player) {
        return getGroups(player.getName());
    }

    /**
     * Get active groups of Player (groups from inactive worlds will be excluded)
     *
     * @param player
     * @return
     */
    public static List<String> getGroups(String player) {
        List<String> list = new ArrayList<>();
        User user = getUser(player);
        user.getAllGroups().forEach(g -> list.add(g.getName()));
        return list;
    }

    /**
     * Get list of prefixes (sorted by priority)
     *
     * @param player
     * @return
     */
    public static List<String> getPrefixes(String player) {
        List<String> list = new ArrayList<>();
        User user = getUser(player);
        Set<BaseNode> nodes = user.getAllNodes();
        nodes.forEach(p -> {
            if (!p.getPrefix().isEmpty()) {
                list.add(p.getPrefix());
            }
        });
        return list;
    }

    /**
     * Get list of suffixes (sorted by priority)
     *
     * @param player
     * @return
     */
    public static List<String> getSuffixes(Player player) {
        return getSuffixes(player.getName());
    }

    /**
     * Get list of suffixes (sorted by priority)
     *
     * @param player
     * @return
     */
    public static List<String> getSuffixes(String player) {
        List<String> list = new ArrayList<>();
        User user = getUser(player);
        Set<BaseNode> passes = user.getAllNodes();
        passes.forEach(p -> {
            if (!p.getSuffix().isEmpty()) {
                list.add(p.getSuffix());
            }
        });
        return list;
    }

    /**
     * Get one prefix with highest priority
     *
     * @param player
     * @return
     */
    public static String getPrefix(String player) {
        List<String> prefixes = getPrefixes(player);
        return prefixes.isEmpty() ? "" : prefixes.get(0);
    }

    /**
     * Get one prefix with highest priority
     *
     * @param player
     * @return
     */
    public static String getPrefix(Player player) {
        return getPrefix(player.getName());
    }

    /**
     * Get one suffix with highest priority
     *
     * @param player
     * @return
     */
    public static String getSuffix(Player player) {
        return getPrefix(player.getName());
    }

    /**
     * Check is player is related to group or not
     *
     * @param player - Player
     * @param group  - Group name
     * @return - true - player in group
     */
    public static boolean isInGroup(Player player, String group) {
        if (player == null) return false;
        User user = getUser(player.getName());
        if (user == null) return false;
        if (user.inGroup(group)) return true;
        return MultipassPlugin.getCfg().enableWorldSupport ? user.inGroup(player.getLevel().getName(), group) : false;
    }

    /**
     * Check is player is related to group or not
     *
     * @param player Player name
     * @param group  Group name
     * @return true - player in group
     */
    public static boolean isInGroup(String player, String group) {
        return isInGroup(Server.getInstance().getPlayerExact(player), group);
    }

    /**
     * Check is player is a member of the default group
     *
     * @param player Player object
     * @return true - player is in default group
     */
    public boolean isInDefaultGroup(Player player) {
        if (player == null) return false;
        return isInDefaultGroup(player.getName());
    }

    /**
     * Check is player is a member of the default group
     *
     * @param player Player name
     * @return true - player is in default group
     */
    public boolean isInDefaultGroup(String player) {
        User user = Users.getUser(player);
        if (user == null) return false;
        for (String group : user.getGroupList()) if (Groups.isDefault(group)) return true;
        return false;
    }

    /**
     * Set player permission. Use prefix "-" to define negative permission
     *
     * @param player     Player object
     * @param permission Permission
     */
    public static void setPermission(Player player, String permission) {
        setPermission(player.getName(), permission);
    }

    /**
     * Set player permission. Use prefix "-" to define negative permission
     *
     * @param player     Player name
     * @param permission Permissions
     */
    public static void setPermission(String player, String permission) {
        Users.setPermission(player, permission);
    }

    /**
     * Set player permission related to defined world
     *
     * @param world      World name
     * @param player     Player name
     * @param permission Permission
     */
    public static void setPermission(String world, String player, String permission) {
        Users.setPermission(world, player, permission);
    }

    /**
     * Move player into a group. Player will be removed from any other group.
     *
     * @param player Player object
     * @param group  Group name
     */
    public static void setGroup(Player player, String group) {
        setGroup(player.getName(), group);
    }

    /**
     * Move player into a group. Player will be removed from any other group.
     *
     * @param player Player name
     * @param group  Group name
     */
    public static void setGroup(String player, String group) {
        Users.setGroup(player, group);
    }

    /**
     * Move player into a group (related to provided world). Player will be removed from any other group.
     *
     * @param world  World name
     * @param player Player name
     * @param group  Group name
     */
    public static void setGroup(String world, String player, String group) {
        Users.setGroup(world, player, group);
    }

    public static void addGroup(Player player, String group) {
        addGroup(player.getName(), group);
    }

    public static void addGroup(String player, String group) {
        Users.addGroup(player, group);
    }

    public static void addGroup(String world, String player, String group) {
        Users.addGroup(world, player, group);
    }

    public static void removePermission(Player player, String permission) {
        removePermission(player.getName(), permission);
    }

    public static void removePermission(String player, String permission) {
        Users.removePermission(player, permission);
    }

    public static void removePermission(String world, String player, String permission) {
        Users.removePermission(world, player, permission);
    }

    public static void removeGroup(Player player, String group) {
        removeGroup(player.getName(), group);
    }

    public static void removeGroup(String player, String group) {
        Users.removeGroup(player, group);
    }

    public static void removeGroup(String world, String player, String group) {
        Users.removeGroup(world, player, group);
    }

    /**
     * Set player prefix/suffix/priority
     *
     * @param player   - Player name
     * @param prefix   - null values will be ignored
     * @param suffix   - null values will be ignored
     * @param priority - negative values will be ignored
     */
    public static void setPlayerPrefix(String player, String prefix, String suffix, int priority) {
        User user = getUser(player);
        if (prefix != null) user.setPrefix(prefix);
        if (suffix != null) user.setSuffix(suffix);
        if (priority >= 0) user.setPriority(priority);
    }

    /**
     * Set player prefix/suffix/priority
     *
     * @param player   - Player name
     * @param prefix   - null values will be ignored
     * @param suffix   - null values will be ignored
     * @param priority - negative values will be ignored
     */
    public static void setPlayerPrefix(Player player, String prefix, String suffix, int priority) {
        setPlayerPrefix(player.getName(), prefix, suffix, priority);
    }

    /**
     * Set player prefix/suffix/priority
     *
     * @param group    - Group id
     * @param prefix   - null values will be ignored
     * @param suffix   - null values will be ignored
     * @param priority - negative values will be ignored
     */
    public static void setGroupPrefix(String group, String prefix, String suffix, int priority) {
        Group g = Groups.getGroup(group);
        if (g == null) return;
        if (prefix != null) g.setPrefix(prefix);
        if (suffix != null) g.setSuffix(suffix);
        if (priority >= 0) g.setPriority(priority);
    }

    /**
     * Get group's prefix.
     *
     * @param group - Group id
     * @return - Prefix. If group not exist - prefix is Empty
     */
    public static String getGroupPrefix(String group) {
        Group g = Groups.getGroup(group);
        if (g == null) return "";
        return g.getPrefix();
    }

    /**
     * Get group's suffix.
     *
     * @param group - Group id
     * @return - Suffix. If group not exist - suffix is Empty
     */
    public static String getGroupSuffix(String group) {
        Group g = Groups.getGroup(group);
        if (g == null) return "";
        return g.getSuffix();
    }

    /**
     * Get group's priority
     *
     * @param group - Group id
     * @return - Priority. If group not exist - suffix is Empty
     */
    public static int getGroupPriority(String group) {
        Group g = Groups.getGroup(group);
        if (g == null) return -1;
        return g.getPriority();
    }

    /**
     * Get group with highest priority
     *
     * @param player
     * @return
     */
    public static String getGroup(Player player) {
        return getGroup(player.getName());
    }

    /**
     * Get group with highest priority
     *
     * @param player
     * @return
     */
    public static String getGroup(String player) {
        List<String> groups = getGroups(player);
        return groups.isEmpty() ? "" : groups.get(0);
    }

    /**
     * Check group existence
     *
     * @param group - group id
     * @return - group exists or not
     */
    public static boolean isGroupExist(String group) {
        return Groups.exist(group);
    }
}