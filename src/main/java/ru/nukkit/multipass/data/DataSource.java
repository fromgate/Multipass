package ru.nukkit.multipass.data;

import ru.nukkit.multipass.permissions.Group;
import ru.nukkit.multipass.permissions.User;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Igor on 17.04.2016.
 */
public interface DataSource {

    void saveUser(User user);

    User loadUser(String playerName);

    void saveGroups(Collection<Group> all);

    Map<String, Group> loadGroups();

    boolean isStored(String userName);
}
