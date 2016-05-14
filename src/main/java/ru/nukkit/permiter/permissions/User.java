package ru.nukkit.permiter.permissions;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.permission.PermissionAttachment;
import ru.nukkit.permiter.MultipassPlugin;
import ru.nukkit.permiter.util.Message;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Igor on 16.04.2016.
 */
public class User extends BasePass {

    PermissionAttachment attachment;

    public User(String playerName) {
        super(playerName);
    }


    public void recalculatePermissions() {
        Message.debugMessage("Recalculate permissions:", this.getName());
        if (getAttachment() == null) return;
        attachment.clearPermissions();
        Player player = Server.getInstance().getPlayerExact(this.name);
        String world = useWorlds() ? player.getLevel().getName() : null;

        Set<Permission> perms = new HashSet<>();
        Group worldGroup = Groups.getGroup(MultipassPlugin.getCfg().defaultGroup);
        if (worldGroup != null) perms.addAll(worldGroup.getPermissions());
        if (world!=null) perms.addAll(worldGroup.getPermissions(world));



        Iterator<Group> iterator = groups.iterator();
        while (iterator.hasNext()) {
            Group group = iterator.next();
            if (group == null || !Groups.exist(group.getName()))
                iterator.remove();
            else perms.addAll(group.getPermissions());
        }
        perms.addAll(permissions);
        perms.forEach(p -> {
            attachment.setPermission(p.getName(), p.isPositive());
            Message.debugMessage(p.isPositive() ? p.getName() : "-" + p.getName());
        });
    }


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

    private boolean useWorlds(){
        return MultipassPlugin.getCfg().enableWorldSupport;
    }

}
