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

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.scheduler.AsyncTask;
import ru.nukkit.multipass.MultipassPlugin;
import ru.nukkit.multipass.data.dblib.DbLibProvider;
import ru.nukkit.multipass.permissions.Group;
import ru.nukkit.multipass.permissions.Groups;
import ru.nukkit.multipass.permissions.User;
import ru.nukkit.multipass.permissions.Users;
import ru.nukkit.multipass.util.TimeUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DatabaseSource extends DataSource {

    DbLibProvider provider;

    public DatabaseSource() {
        this.provider = new DbLibProvider();
        if (this.provider.isEnabled()) runRecheck();
    }

    private void runRecheck(){
        Long rescanTime = TimeUtil.parseTime(MultipassPlugin.getCfg().multiServerRecheck);
        if (rescanTime<=0) return;
        int delay = Math.max(TimeUtil.timeToTicks(rescanTime).intValue(), 20);
        Server.getInstance().getScheduler().scheduleDelayedRepeatingTask(new Runnable() {
            @Override
            public void run() {
                Collection<Group> groups = null;
                List<User> users = new ArrayList<>();
                try {
                    groups = provider.loadGroups();
                    for (Player player : Server.getInstance().getOnlinePlayers().values()){
                        User user = provider.loadUser(player.getName());
                        if (user!=null) users.add(user);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (groups!=null) updateAllGroups(groups);
                if (users!=null&&!users.isEmpty()) updateUsers(users);
            }
        }, delay, delay, true);
    }

    @Override
    public void saveUser(final User user) {
        if (!provider.isEnabled()) return;
        Server.getInstance().getScheduler().scheduleAsyncTask(new AsyncTask() {
            @Override
            public void onRun() {
                try {
                    provider.saveUser(user);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public User loadUser(String playerName) {
        if (provider.isEnabled())
            Server.getInstance().getScheduler().scheduleAsyncTask(new AsyncTask() {
                @Override
                public void onRun() {
                    User user = null;
                    try {
                        user = provider.loadUser(playerName);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (user!=null) updateUser(user);
                }
            });
        return new User(playerName);
    }

    @Override
    public void saveGroups(Collection<Group> all) {
        if (!provider.isEnabled()) return;
        Server.getInstance().getScheduler().scheduleAsyncTask(new AsyncTask() {
            @Override
            public void onRun() {
                try {
                    provider.saveGroups(all);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public Collection<Group> loadGroups() {
        if (provider.isEnabled())
            Server.getInstance().getScheduler().scheduleAsyncTask(new AsyncTask() {
                @Override
                public void onRun() {
                    Collection<Group> groups = null;
                    try {
                        groups = provider.loadGroups();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (groups!=null) updateAllGroups(groups);
                }
            });
        Collection<Group> groups = Groups.getAll();
        return groups == null ? Collections.EMPTY_LIST : groups;
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

    @Override
    public boolean isEnabled() {
        return provider.isEnabled();
    }

    @Override
    public void clearUsers() {
        if (provider.isEnabled())
            try {
                provider.clearUsers();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void clearGroups() {
        if (provider.isEnabled())
            try {
                provider.clearGroups();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public void updateAllGroups(Collection<Group> groups) {
        Server.getInstance().getScheduler().scheduleTask(new Runnable() {
            @Override
            public void run() {
                Groups.updateGroups(groups);
            }
        });
    }

    public void updateUsers(final Collection<User> users) {
        Server.getInstance().getScheduler().scheduleTask(new Runnable() {
            @Override
            public void run() {
                users.forEach(user -> Users.setUser(new User(user.getName(), user)));
            }
        });
    }

    public void updateUser(final User user) {
        Server.getInstance().getScheduler().scheduleTask(new Runnable() {
            @Override
            public void run() {
                Users.setUser(new User(user.getName(), user));
            }
        });
    }
}