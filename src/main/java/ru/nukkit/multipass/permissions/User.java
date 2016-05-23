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

package ru.nukkit.multipass.permissions;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.permission.PermissionAttachment;
import ru.nukkit.multipass.MultipassPlugin;
import ru.nukkit.multipass.util.Message;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Igor on 16.04.2016.
 */
public class User extends BaseNode {

    PermissionAttachment attachment;

    public User(String playerName) {
        super(playerName);
    }

    public User(String playerName, Node node) {
        super(playerName, node);
    }


    public void recalculatePermissions() {
        Message.debugMessage("Recalculate permissions:", this.getName());
        if (getAttachment() == null) return;
        attachment.clearPermissions();
        Player player = Server.getInstance().getPlayerExact(this.name);
        String world = useWorlds() ? player.getLevel().getName() : null;

        Set<Permission> perms = new LinkedHashSet<>();
        Set<BaseNode> nodes = this.getAllNodes(false);
        nodes.forEach(node -> {
            Message.debugMessage("node:", node.getName(),node.getPermissions().size());
            perms.addAll(node.getPermissions());
            if (world!=null) node.getPermissions(world);
        });

        /*
        Group worldGroup = Groups.getGroup(MultipassPlugin.getCfg().defaultGroup);

        Set<Group> allGroups = new TreeSet<>(new HighBase());

        if (worldGroup!=null) allGroups.add(worldGroup);
        allGroups.addAll(this.getAllGroups());

        Iterator<Group> iterator = groups.iterator();
        while (iterator.hasNext()) {
            Group group = iterator.next();
            if (group == null || !Groups.exist(group.getName())) {
                Message.REMOVED_GROUP_DETECTED.log(group == null ? "null" : group.getName(), player.getName());
                iterator.remove();
            } else {
                perms.addAll(group.getPermissions());
                if (world!=null) perms.addAll (group.getPermissions(world));
            }
        }
        perms.addAll(getPermissions());
        if (world!=null) perms.addAll (getPermissions(world));
        */

        perms.forEach(p -> {
            attachment.setPermission(p.getName(), p.isPositive());
            Message.debugMessage(p.isPositive() ? p.getName() : "-" + p.getName());
        });
    }


 /*   public void recalculatePermissions() {
        Message.debugMessage("Recalculate permissions:", this.getName());
        if (getAttachment() == null) return;
        attachment.clearPermissions();
        Player player = Server.getInstance().getPlayerExact(this.name);
        String world = useWorlds() ? player.getLevel().getName() : null;

        Set<Permission> perms = new HashSet<>();

        Group worldGroup = Groups.getGroup(MultipassPlugin.getCfg().defaultGroup);

        Set<Group> allGroups = new TreeSet<>(new HighBase());

        if (worldGroup!=null) allGroups.add(worldGroup);
        allGroups.addAll(this.getAllGroups());

        Iterator<Group> iterator = groups.iterator();
        while (iterator.hasNext()) {
            Group group = iterator.next();
            if (group == null || !Groups.exist(group.getName())) {
                Message.REMOVED_GROUP_DETECTED.log(group == null ? "null" : group.getName(), player.getName());
                iterator.remove();
            } else {
                perms.addAll(group.getPermissions());
                if (world!=null) perms.addAll (group.getPermissions(world));
            }
        }
        perms.addAll(getPermissions());
        if (world!=null) perms.addAll (getPermissions(world));
        perms.forEach(p -> {
            attachment.setPermission(p.getName(), p.isPositive());
            Message.debugMessage(p.isPositive() ? p.getName() : "-" + p.getName());
        });
    } */


    public PermissionAttachment getAttachment() {
        Player player = Server.getInstance().getPlayerExact(this.name);
        attachment = player == null ? null : player.addAttachment(MultipassPlugin.getPlugin());
        return attachment;
    }

    public boolean inGroup(String groupStr) {
        Group group = Groups.getGroup(groupStr);
        if (group == null) return false;
        return groups.contains(group);
    }

    public boolean inGroup(String world, String groupStr){
        if (world == null) return false;
        Group group = Groups.getGroup(groupStr);
        if (group == null) return false;
        Node node = getWorldPass(world);
        if (node == null) return false;
        return node.groups.contains(group);
    }

    private boolean useWorlds(){
        return MultipassPlugin.getCfg().enableWorldSupport;
    }

    public boolean isEmpty() {
        return this.permissions.isEmpty() &&
                (groups.isEmpty()||(groups.size()==1&&(groups.stream().toArray(Group[]::new)[0].isDefault()))) &&
                this.getWorldPass().isEmpty() &&
                this.prefix.isEmpty() && this.suffix.isEmpty() && (this.priority == 0);
    }
/*


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return name != null ? name.equalsIgnoreCase(that.name) : false;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
    */

}
