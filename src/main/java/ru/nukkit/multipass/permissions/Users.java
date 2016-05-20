package ru.nukkit.multipass.permissions;

import cn.nukkit.Player;
import cn.nukkit.Server;
import ru.nukkit.multipass.data.DataProvider;
import ru.nukkit.multipass.event.PermissionsUpdateEvent;
import ru.nukkit.multipass.util.Message;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;


/**
 * Created by Igor on 03.05.2016.
 */
public class Users {

    private static Map<String, User> users = new TreeMap<String, User>();

    public static void loadUser(Player player) {
        loadUser(player.getName());
    }

    public static void loadUser(String playerName) {
        User user = users.containsKey(playerName) ? users.get(playerName) : DataProvider.loadUser(playerName);
        user.recalculatePermissions();
        users.put(playerName, user);
    }


    public static User getUser(String playerName) {
        if (!users.containsKey(playerName)) loadUser(playerName);
        return users.get(playerName);
    }

    public static void closeUser(Player player) {
        if (users.containsKey(player.getName())) users.remove(player.getName());
    }

    public static void closeOfflineUsers() {
        for (Iterator<Map.Entry<String, User>> iterator = users.entrySet().iterator(); iterator.hasNext(); ) {
            if (Server.getInstance().getPlayerExact(iterator.next().getKey()) == null) iterator.remove();
        }
    }

    public static boolean isRegistered(String userName) {
        if (users.containsKey(userName)) return true;
        return (DataProvider.isRegistered(userName));
    }

    public static void addGroup(String id, String group) {
        User user = Users.getUser(id);
        user.setGroup(group);
        saveUser(user);
    }

    public static boolean inGroup(String userName, String group) {
        User user = Users.getUser(userName);
        return user.inGroup(group);
    }

    public static boolean inGroup(String world, String userName, String group) {
        User user = Users.getUser(userName);
        return user.inGroup(world, group);
    }

    public static void removeGroup(String userName, String group) {
        User user = Users.getUser(userName);
        if (user.inGroup(group)) user.removeGroup(group);
        saveUser(user);
    }

    public static void removeGroup(String world, String userName, String group) {
        User user = Users.getUser(userName);
        if (user.inGroup(group)) user.removeGroup(world, group);
        saveUser(user);
    }

    public static boolean isPermissionSet(String userName, String permStr) {
        User user = Users.getUser(userName);
        return user.isPermissionSet(permStr);
    }

    public static void removePermission(String userName, String permStr) {
        User user = Users.getUser(userName);
        user.removePermission(permStr);
        saveUser(user);
    }

    public static void removePermission(String world, String userName, String permStr) {
        User user = Users.getUser(userName);
        user.removePermission(permStr);
        saveUser(user);
    }


    public static void setGroup(String userName, String groupStr) {
        User user = Users.getUser(userName);
        user.setGroup(groupStr);
        saveUser(user);
    }

    public static void setGroup(String world, String userName, String groupStr) {
        User user = Users.getUser(userName);
        user.setGroup(groupStr);
        saveUser(user);
    }

    public static void setPrefix(String userName, String prefix){
        User user = Users.getUser(userName);
        user.setPrefix(prefix);
        saveUser(user);
    }

    public static void setSuffix(String userName, String suffix){
        User user = Users.getUser(userName);
        user.setSuffix(suffix);
        saveUser(user);
    }

    public static void setPriority (String userName, int priority){
        User user = Users.getUser(userName);
        user.setPriority(priority);
        saveUser(user);
    }

    public static void recalculatePermissions(String name) {
        User user = Users.getUser(name);
        user.recalculatePermissions();
    }

    public static void setPermission(String userName, String permStr) {
        User user = Users.getUser(userName);
        user.setPermission(permStr);
        saveUser(user);
    }

    public static void setPermission(String world, String userName, String permission) {
        User user = Users.getUser(userName);
        user.setPermission(world,permission);
        saveUser(user);
    }

    public static void reloadUsers() {
        users.clear();
        Server.getInstance().getOnlinePlayers().values().forEach(player -> loadUser(player.getName()));
    }

    public static void recalculatePermissions() {
        Message.debugMessage("Recalculating online users permissions");
        Server.getInstance().getOnlinePlayers().values().forEach(player -> {
            User user = getUser(player.getName());
            user.recalculatePermissions();
        });
    }


    public static void saveUser(User user) {
        Message.debugMessage("Saving user: " + user.getName());
        DataProvider.saveUser(user);
        user.recalculatePermissions();
        PermissionsUpdateEvent event = new PermissionsUpdateEvent(user.getName());
        Server.getInstance().getPluginManager().callEvent(event);
    }
}
