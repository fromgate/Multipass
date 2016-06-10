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

package ru.nukkit.multipass.data;

import ru.nukkit.multipass.MultipassPlugin;
import ru.nukkit.multipass.permissions.Group;
import ru.nukkit.multipass.permissions.Groups;
import ru.nukkit.multipass.permissions.User;
import ru.nukkit.multipass.util.Message;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public enum DataProvider {

    YAML(YamlSource.class),
    DATABASE(DatabaseSource.class);

    private Class<? extends DataSource> clazz;
    private DataSource source;

    DataProvider(Class<? extends DataSource> clazz) {
        this.clazz = clazz;
    }

    public DataSource getSource() {
        try {
            source = clazz.newInstance();
        } catch (Exception e) {
            source = null;
            Message.PROVIDER_FAILED.log(this.name());
        }
        return this.source;
    }

    private static DataSource currentProvider;


    public static void init() {
        DataProvider dp = getByName(MultipassPlugin.getCfg().dataSource);
        if (dp == null) {
            Message.LOG_UNKNOWN_DATAPROVIDER.log(MultipassPlugin.getCfg().dataSource);
            dp = YAML;
        } else Message.LOG_DATAPROVIDER.log(dp.name());
        currentProvider = dp.getSource();
    }

    public static User loadUser(String playerName) {
        return currentProvider.loadUser(playerName);
    }

    public static boolean isRegistered(String userName) {
        return currentProvider.isStored(userName);
    }

    public static void saveUser(User user) {
        currentProvider.saveUser(user);
    }

    public static void saveUsers(Collection<User> users){

    }

    public static void saveGroups() {
        currentProvider.saveGroups(Groups.getAll());
    }

    public static Map<String, Group> loadGroups() {
        Map<String, Group> groups = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        currentProvider.loadGroups().forEach(group -> groups.put(group.getName(), group));
        return groups;
    }

    public static DataProvider getByName(String name) {
        for (DataProvider dp : values())
            if (name.equalsIgnoreCase(dp.name())) return dp;
        return null;
    }

    public static void clearUsers() {
        currentProvider.clearUsers();
    }

    public static void clearGroups(){
        currentProvider.clearGroups();
    }

    public static Collection<User> getAllUsers() {
        return currentProvider.getAllUsers();
    }
}
