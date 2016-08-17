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

package ru.nukkit.multipass.data.dblib.tables;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import ru.nukkit.multipass.permissions.User;

@DatabaseTable(tableName = "mp_users")
public class UsersTable {

    @DatabaseField(id = true, canBeNull = false)
    String name;

    @DatabaseField
    String prefix;

    @DatabaseField
    String suffix;

    @DatabaseField
    int priority;

    @ForeignCollectionField
    ForeignCollection<UsersPermTable> permissions;

    @ForeignCollectionField
    ForeignCollection<UsersGroupTable> groups;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public ForeignCollection<UsersPermTable> getPermissions() {
        return this.permissions;
    }

    /*
    public List<UsersPermTable> getPermissions() {
        List<UsersPermTable> perms = new ArrayList<>();

        if (permissions == null) return perms;
        permissions.forEach(p -> perms.add(p));
        return perms;
    } */


    public ForeignCollection<UsersGroupTable> getGroups() {
        return this.groups;
    }

    /*
    public List<UsersGroupTable> getGroups() {
        List<UsersGroupTable> grps = new ArrayList<>();
        if (groups == null) return grps;
        groups.forEach(g -> grps.add(g));
        return grps;
    } */

    UsersTable() {
    }

    public UsersTable(String name) {
        this.name = name;
        this.prefix = "";
        this.suffix = "";
        this.priority = 0;
    }

    public UsersTable(User user) {
        this.name = user.getName();
        this.prefix = user.getPrefix();
        this.suffix = user.getSuffix();
        this.priority = user.getPriority();
    }
}