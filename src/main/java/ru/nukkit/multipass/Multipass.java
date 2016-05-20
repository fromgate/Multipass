package ru.nukkit.multipass;

import cn.nukkit.Player;
import cn.nukkit.Server;
import ru.nukkit.multipass.permissions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Igor on 12.05.2016.
 */
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
     * @param player
     * @return
     */
    public static List<String> getGroups(String player) {
        List<String> list = new ArrayList<>();
        User user = Users.getUser(player);
        user.getAllGroups().forEach(g -> list.add(g.getName()));
        return list;
    }

    /**
     * Get list of prefixes (sorted by priority)
     *
     * @param player
     * @return
     */
    public static List<String> getPrefixes (String player){
        List<String> list = new ArrayList<>();
        User user = Users.getUser(player);
        Set<BasePass> passes = user.getAllPasses();
        passes.forEach(p -> {
            if (!p.getPrefix().isEmpty()) list.add(p.getPrefix());
        });
        return list;
    }

    /**
     * Get list of suffixes (sorted by priority)
     *
     * @param player
     * @return
     */
    public static List<String> getSuffixes (Player player){
        return getSuffixes(player.getName());
    }

    /**
     * Get list of suffixes (sorted by priority)
     *
     * @param player
     * @return
     */
    public static List<String> getSuffixes (String player){
        List<String> list = new ArrayList<>();
        User user = Users.getUser(player);
        Set<BasePass> passes = user.getAllPasses();
        passes.forEach(p -> {
            if (!p.getSuffix().isEmpty()) list.add(p.getSuffix());
        });
        return list;
    }

    /**
     * Get one prefix with highest priority
     *
     * @param player
     * @return
     */
    public static String getPrefix(String player){
        List<String> prefixes = getPrefixes(player);
        return prefixes.isEmpty() ? "" : prefixes.get(0);
    }

    /**
     * Get one prefix with highest priority
     *
     * @param player
     * @return
     */
    public static String getPrefix(Player player){
        return getPrefix (player.getName());
    }

    /**
     * Get one suffix with highest priority
     *
     * @param player
     * @return
     */
    public static String getSuffix(Player player){
        return getPrefix(player.getName());
    }

    /**
     * Check is player is related to group or not
     *
     * @param player - Player
     * @param group - Group name
     * @return - true - player in group
     */
    public static boolean isInGroup(Player player, String group){
        if (player == null) return false;
        User user = Users.getUser(player.getName());
        if (user == null) return false;
        String world = MultipassPlugin.getCfg().enableWorldSupport ?  player.getLevel().getName() : null;
        return user.inGroup(group) || user.inGroup(world,group);
    }

    /**
     * Check is player is related to group or not
     *
     * @param player - Player name
     * @param group - Group name
     * @return - true - player in group
     */
    public static boolean isInGroup(String player, String group){
        return isInGroup (Server.getInstance().getPlayerExact(player),group);
    }

    public static void setPermission (Player player, String permission){
        setPermission(player.getName(),permission);
    }

    public static void setPermission (String player, String permission){
        Users.setPermission(player,permission);
    }

    public static void setPermission (String world, String player, String permission){
        Users.setPermission(world, player, permission);
    }

    public static void setGroup (Player player, String group){
        setGroup(player.getName(),group);
    }

    public static void setGroup (String player, String group){
        Users.setGroup(player,group);
    }

    public static void setGroup (String world, String player, String group){
        Users.setGroup(world, player,group);
    }



    public static void removePermission (Player player, String permission){
        removePermission(player.getName(),permission);
    }

    public static void removePermission (String player, String permission){
        Users.removePermission(player,permission);
    }

    public static void removePermission (String world, String player, String permission){
        Users.removePermission(world,player, permission);
    }

    public static void removeGroup (Player player, String group){
        removeGroup(player.getName(),group);
    }

    public static void removeGroup (String player, String group){
        Users.removeGroup(player,group);
    }

    public static void removeGroup (String world, String player, String group){
        Users.removeGroup(world, player, group);
    }

    /**
     * Set player prefix/suffix/priority
     *
     * @param player - Player name
     * @param prefix - null values will be ignored
     * @param suffix - null values will be ignored
     * @param priority - negative values will be ignored
     */
    public static void setPlayerPrefix (String player, String prefix, String suffix, int priority){
        User user = Users.getUser(player);
        if (prefix!=null) user.setPrefix(prefix);
        if (suffix!=null) user.setSuffix(suffix);
        if (priority>=0) user.setPriority(priority);
    }

    /**
     * Set player prefix/suffix/priority
     *
     * @param player - Player name
     * @param prefix - null values will be ignored
     * @param suffix - null values will be ignored
     * @param priority - negative values will be ignored
     */
    public static void setPlayerPrefix (Player player, String prefix, String suffix, int priority){
        setPlayerPrefix(player.getName(), prefix, suffix, priority);
    }

    /**
     * Set player prefix/suffix/priority
     *
     * @param group - Group id
     * @param prefix - null values will be ignored
     * @param suffix - null values will be ignored
     * @param priority - negative values will be ignored
     */
    public static void setGroupPrefix (String group, String prefix, String suffix, int priority){
        Group g = Groups.getGroup(group);
        if (g == null) return;
        if (prefix!=null) g.setPrefix(prefix);
        if (suffix!=null) g.setSuffix(suffix);
        if (priority>=0) g.setPriority(priority);
    }

    /**
     * Get group's prefix.
     * @param group - Group id
     * @return - Prefix. If group not exist - prefix is Empty
     */
    public static String getGroupPrefix (String group){
        Group g = Groups.getGroup(group);
        if (g == null) return "";
        return g.getPrefix();
    }

    /**
     * Get group's suffix.
     * @param group - Group id
     * @return - Suffix. If group not exist - suffix is Empty
     */
    public static String getGroupSuffix (String group){
        Group g = Groups.getGroup(group);
        if (g == null) return "";
        return g.getSuffix();
    }

    /**
     * Get group's priority
     * @param group - Group id
     * @return - Priority. If group not exist - suffix is Empty
     */
    public static int getGroupPriority (String group){
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
    public static String getGroup(Player player){
        return getGroup(player.getName());
    }

    /**
     *  Get group with highest priority
     *
     * @param player
     * @return
     */
    public static String getGroup(String player){
        List<String> groups = getGroups(player);
        return groups.isEmpty() ? "" : groups.get(0);
    }
}