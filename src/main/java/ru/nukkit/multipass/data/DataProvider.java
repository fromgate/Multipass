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

import java.util.Map;

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
