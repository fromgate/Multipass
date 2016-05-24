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

import ru.nukkit.multipass.permissions.Group;
import ru.nukkit.multipass.permissions.Groups;
import ru.nukkit.multipass.permissions.User;
import ru.nukkit.multipass.util.Message;

import java.util.Map;

public enum DataProvider {

    YAML(YamlSource.class);
    // TODO - DbLib support

    private DataSource source;

    DataProvider(Class<? extends DataSource> clazz) {
        try {
            source = clazz.newInstance();
        } catch (Exception e) {
            source = null;
            Message.PROVIDER_FAILED.log(clazz.getName());
        }
    }

    public DataSource getSource() {
        return this.source;
    }

    private static DataSource currentProvider;


    public static void init() {
        currentProvider = YAML.getSource();
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

    public static void saveGroups() {
        currentProvider.saveGroups(Groups.getAll());
    }

    public static Map<String, Group> loadGroups() {
        return currentProvider.loadGroups();
    }
}
