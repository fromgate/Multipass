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

import cn.nukkit.Server;
import cn.nukkit.scheduler.Task;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;
import com.j256.ormlite.table.TableUtils;
import ru.nukkit.dblib.DbLib;
import ru.nukkit.multipass.MultipassPlugin;
import ru.nukkit.multipass.data.dblib.tables.*;
import ru.nukkit.multipass.permissions.Group;
import ru.nukkit.multipass.permissions.Permission;
import ru.nukkit.multipass.permissions.User;
import ru.nukkit.multipass.util.Cfg;
import ru.nukkit.multipass.util.Message;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static ru.nukkit.dblib.DbLib.getConnectionSource;

public class DbLibProvider {

    private boolean enabled;

    private Task rescan;

    private ConnectionSource connection;

    Dao<UsersTable, String> usersDao;
    Dao<GroupsTable, String> groupsDao;

    Dao<UsersPermTable, String> usersPerm;
    Dao<GroupsPermTable, String> groupsPerm;

    Dao<UsersGroupTable, String> usersGroup;
    Dao<GroupsGroupTable, String> groupsGroup;


    private Cfg cfg;

    public DbLibProvider() {
        this.enabled = false;
        this.cfg = MultipassPlugin.getCfg();
        if (Server.getInstance().getPluginManager().getPlugin("DbLib") == null) {
            Message.DB_DBLIB_NOTFOUND.log();
        }
        initConnection();
        if (connection == null) return;
        try {
            // USERS_PERM
            DatabaseTableConfig<UsersPermTable> usersPermCfg = new DatabaseTableConfig(UsersPermTable.class, cfg.tablePrefix + "users_perm", null);
            usersPerm = DaoManager.createDao(connection, usersPermCfg);
            TableUtils.createTableIfNotExists(connection, usersPermCfg);

            // GROUPS_PERM
            DatabaseTableConfig<GroupsPermTable> groupsPermCfg = new DatabaseTableConfig(GroupsPermTable.class, cfg.tablePrefix + "groups_perm", null);
            groupsPerm = DaoManager.createDao(connection, groupsPermCfg);
            TableUtils.createTableIfNotExists(connection, groupsPermCfg);

            // USERS_GROUP
            DatabaseTableConfig<UsersGroupTable> usersGroupCfg = new DatabaseTableConfig(UsersGroupTable.class, cfg.tablePrefix + "users_group", null);
            usersGroup = DaoManager.createDao(connection, usersGroupCfg);
            TableUtils.createTableIfNotExists(connection, usersGroupCfg);

            // GROUPS_GROUP
            DatabaseTableConfig<GroupsGroupTable> groupsGroupCfg = new DatabaseTableConfig(GroupsGroupTable.class, cfg.tablePrefix + "groups_group", null);
            groupsGroup = DaoManager.createDao(connection, groupsGroupCfg);
            TableUtils.createTableIfNotExists(connection, groupsGroupCfg);

            // USERS
            DatabaseTableConfig<UsersTable> usersTableCfg = new DatabaseTableConfig(UsersTable.class, cfg.tablePrefix + "users", null);
            usersDao = DaoManager.createDao(connection, usersTableCfg);
            TableUtils.createTableIfNotExists(connection, usersTableCfg);

            // GROUPS
            DatabaseTableConfig<GroupsTable> groupsTableCfg = new DatabaseTableConfig(GroupsTable.class, cfg.tablePrefix + "groups", null);
            groupsDao = DaoManager.createDao(connection, groupsTableCfg);
            TableUtils.createTableIfNotExists(connection, groupsTableCfg);

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        enabled = true;
    }

    private void initConnection() {
        switch (cfg.dblibSource.toLowerCase()) {
            case "mysql":
                connection = DbLib.getConnectionSourceMySql(cfg.mysqlHost,
                        (cfg.mysqlPort <= 0 ? cfg.mysqlPort : 3306),
                        cfg.mysqlDb,
                        cfg.mysqlName,
                        cfg.mysqlPassword);
                return;
            case "sqlite":
                connection = DbLib.getConnectionSourceSQLite(MultipassPlugin.getPlugin(), cfg.customSQLite);
                return;

        }
        connection = getConnectionSource();
    }

    public boolean isEnabled() {
        return enabled;
    }


    public void saveUser(User user) throws SQLException {
        UsersTable userRecord = usersDao.queryForId(user.getName());
        if (userRecord == null) userRecord = new UsersTable(user);
        List<UsersPermTable> perms = userRecord.getPermissions();
        usersPerm.delete(perms);
        for (Map.Entry<String, List<Permission>> e : user.getPermissionsMap().entrySet()) {
            for (Permission p : e.getValue())
                usersPerm.create(new UsersPermTable(userRecord, e.getKey(), p.getName(), p.isPositive()));
        }

        List<UsersGroupTable> groups = userRecord.getGroups();

        usersGroup.delete(groups);

        for (Map.Entry<String, List<String>> e : user.getGroupMap().entrySet()) {
            for (String g : e.getValue())
                usersGroup.create(new UsersGroupTable(userRecord, e.getKey(), g));
        }
        usersDao.createOrUpdate(userRecord);


    }

    public User loadUser(String name) throws SQLException {
        UsersTable userTable = usersDao.queryForId(name);
        if (userTable == null) Message.debugMessage("loadUser: ", "null");
        else Message.debugMessage("loadUser:", userTable.getName(),
                "groups:", userTable.getGroups().size(),
                "permissions:", userTable.getPermissions().size(),
                "prefix:", userTable.getPrefix(),
                "suffix:", userTable.getSuffix(),
                "priority:", userTable.getPriority());
        return userTable == null ? new User(name) : tableToUser(userTable);
    }


    public void saveGroups(Collection<Group> all) throws SQLException {
        if (all == null || all.isEmpty()) return;
        for (Group group : all) {
            GroupsTable groupTable = groupsDao.queryForId(group.getName());
            if (groupTable == null) groupTable = new GroupsTable(group.getName());

            groupsPerm.delete(groupTable.getPermissions());
            groupsGroup.delete(groupTable.getGroups());

            groupTable.setPrefix(group.getPrefix());
            groupTable.setSuffix(group.getSuffix());
            groupTable.setPriority(group.getPriority());
            for (Map.Entry<String, List<Permission>> e : group.getPermissionsMap().entrySet()) {
                for (Permission p : e.getValue())
                    groupsPerm.create(new GroupsPermTable(groupTable, e.getKey(), p.getName(), p.isPositive()));
            }
            for (Map.Entry<String, List<String>> e : group.getGroupMap().entrySet()) {
                for (String g : e.getValue())
                    groupsGroup.create(new GroupsGroupTable(groupTable, e.getKey(), g));
            }
            groupsDao.createOrUpdate(groupTable);
        }
    }


    public Collection<Group> loadGroups() throws SQLException {
        List<Group> groups = new ArrayList<>();
        groupsDao.queryForAll().forEach(g -> {
            Group group = new Group(g.getName());
            group.setPrefix(g.getPrefix());
            group.setSuffix(g.getSuffix());
            group.setPriority(g.getPriority());
            g.getPermissions().forEach(p -> {
                if (p.getWorld() == null || p.getWorld().isEmpty())
                    group.setPermission(p.getPermission(), p.isPositive());
                else group.setPermission(p.getWorld(), p.getPermission(), p.isPositive());
            });
            g.getGroups().forEach(gg -> {
                if (gg.getWorld() == null || gg.getWorld().isEmpty())
                    group.addGroup(gg.getSubgroup());
                else group.addGroup(gg.getWorld(), gg.getSubgroup());
            });
            groups.add(group);
        });
        return groups;
    }

    public boolean isStored(String userName) throws SQLException {
        return usersDao.idExists(userName);
    }

    public Collection<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        usersDao.queryForAll().forEach(u -> users.add(tableToUser(u)));
        return users;
    }

    private User tableToUser(UsersTable userTable) {
        User user = new User(userTable.getName());
        user.setPrefix(userTable.getPrefix());
        user.setSuffix(userTable.getSuffix());
        user.setPriority(userTable.getPriority());
        List<UsersPermTable> userPerms = userTable.getPermissions();
        for (UsersPermTable ut : userPerms) {
            if (ut.getWorld() == null || ut.getWorld().isEmpty())
                user.setPermission(ut.getPermission(), ut.isPositive());
            else user.setPermission(ut.getWorld(), ut.getPermission(), ut.isPositive());
        }
        List<UsersGroupTable> userGroups = userTable.getGroups();
        for (UsersGroupTable ug : userGroups) {
            if (ug.getWorld() == null || ug.getWorld().isEmpty())
                user.addGroup(ug.getSubgroup());
            else user.addGroup(ug.getWorld(), ug.getSubgroup());
        }
        return user;
    }

    public void clearUsers() throws SQLException {
        TableUtils.clearTable(connection, UsersPermTable.class);
        TableUtils.clearTable(connection, UsersGroupTable.class);
        TableUtils.clearTable(connection, UsersTable.class);
    }

    public void clearGroups() throws SQLException {
        TableUtils.clearTable(connection, GroupsPermTable.class);
        TableUtils.clearTable(connection, GroupsGroupTable.class);
        TableUtils.clearTable(connection, GroupsTable.class);
    }
}