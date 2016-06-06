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

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mp_groups_perm")
public class GroupsPermTable {

    @DatabaseField(generatedId = true)
    int id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    GroupsTable group;

    @DatabaseField
    String world;

    @DatabaseField
    String permission;

    @DatabaseField
    boolean positive;

    GroupsPermTable() {
    }

    public GroupsPermTable(GroupsTable group, String world, String permission, boolean positive) {
        this.group = group;
        this.world = world == null ? "" : world;
        this.permission = permission;
        this.positive = positive;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GroupsTable getGroup() {
        return group;
    }

    public void setGroup(GroupsTable group) {
        this.group = group;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public boolean isPositive() {
        return positive;
    }

    public void setPositive(boolean positive) {
        this.positive = positive;
    }
}