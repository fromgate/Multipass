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

package ru.nukkit.multipass.data.dblib;

import ru.nukkit.multipass.data.DataSource;
import ru.nukkit.multipass.permissions.Group;
import ru.nukkit.multipass.permissions.User;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;

public class DbLibSource extends DataSource {


    DbLibProvider provider;

    private boolean syncMode;

    public DbLibSource() {
        this.provider = new DbLibProvider();
    }


    @Override
    public void saveUser(User user) {
        if (!provider.isEnabled()) return;
        try {
            provider.saveUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User loadUser(String playerName) {
        if (provider.isEnabled())
            try {
                return provider.loadUser(playerName);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return new User(playerName);
    }

    @Override
    public void saveGroups(Collection<Group> all) {
        if (!provider.isEnabled()) return;
        try {
            provider.saveGroups(all);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Group> loadGroups() {
        if (provider.isEnabled())
            try {
                return provider.loadGroups();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return Collections.EMPTY_LIST;
    }

    @Override
    public boolean isStored(String userName) {
        if (provider.isEnabled())
            try {
                return provider.isStored(userName);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return false;
    }

    @Override
    public Collection<User> getAllUsers() {
        if (provider.isEnabled())
            try {
                return provider.getAllUsers();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return Collections.EMPTY_LIST;
    }
}