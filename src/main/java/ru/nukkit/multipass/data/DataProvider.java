package ru.nukkit.multipass.data;

import ru.nukkit.multipass.permissions.Group;
import ru.nukkit.multipass.permissions.Groups;
import ru.nukkit.multipass.permissions.User;

import java.util.Map;

/**
 * Created by Igor on 04.05.2016.
 */
public class DataProvider {

    private static DataSource source;

    public static void init() {
        source = new YamlSaver();
    }

    public static User loadUser(String playerName) {
        return source.loadUser(playerName);
    }

    public static boolean isRegistered(String userName) {
        return source.isStored(userName);
    }

    public static void saveUser(User user) {
        source.saveUser(user);
    }

    public static void saveGroups() {
        source.saveGroups(Groups.getAll());
    }

    public static Map<String, Group> loadGroups() {
        return source.loadGroups();
    }
}
