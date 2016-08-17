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
import ru.nukkit.multipass.permissions.Users;

import java.util.Collection;

public abstract class DataSource {

    public abstract void saveUser(User user);

    public abstract User loadUser(String playerName);

    public abstract void saveGroups(Collection<Group> all);

    public abstract Collection<Group> loadGroups();

    public abstract boolean isStored(String userName);

    public abstract Collection<User> getAllUsers();

    public void updateUser(final User user) {
        Users.setUser(new User(user.getName(), user));
    }

    public void updateAllGroups(final Collection<Group> groups) {
        Groups.updateGroups(groups);
    }

    public abstract boolean isEnabled();

    public abstract void clearUsers();

    public abstract void clearGroups();

    public abstract void saveUsers(Collection<User> users);
}
