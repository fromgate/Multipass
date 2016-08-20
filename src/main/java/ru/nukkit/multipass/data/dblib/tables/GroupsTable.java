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


@DatabaseTable(tableName = "mp_groups")
public class GroupsTable {

    @DatabaseField(id = true, canBeNull = false)
    String name;

    @DatabaseField
    String prefix;

    @DatabaseField
    String suffix;

    @DatabaseField
    int priority;

    @ForeignCollectionField
    ForeignCollection<GroupsPermTable> permissions;

    @ForeignCollectionField
    ForeignCollection<GroupsGroupTable> groups;


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

    public ForeignCollection<GroupsPermTable> getPermissions() {
        return this.permissions;
    }

    public void clearPermissions() {
        if (this.permissions != null) this.permissions.clear();
    }


    public ForeignCollection<GroupsGroupTable> getGroups() {
        return this.groups;
    }

    public void clearGroups() {
        if (this.groups != null) this.groups.clear();
    }

   /* public List<GroupsPermTable> getPermissions() {
        List<GroupsPermTable> perms = new ArrayList<>();
        if (permissions == null) return perms;
        permissions.forEach(p -> perms.add(p));
        return perms;
    }

    public List<GroupsGroupTable> getGroups() {
        List<GroupsGroupTable> grps = new ArrayList<>();
        if (groups == null) return grps;
        groups.forEach(g -> grps.add(g));
        return grps;
    } */

    GroupsTable() {
    }

    public GroupsTable(String name) {
        this.name = name;
        this.prefix = "";
        this.suffix = "";
        this.priority = 0;
    }
}