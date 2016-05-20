package ru.nukkit.multipass.permissions;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.permission.PermissionAttachment;
import ru.nukkit.multipass.MultipassPlugin;
import ru.nukkit.multipass.util.HighBase;
import ru.nukkit.multipass.util.Message;

import java.util.*;

/**
 * Created by Igor on 16.04.2016.
 */
public class User extends BasePass {

    PermissionAttachment attachment;

    public User(String playerName) {
        super(playerName);
    }

    public User(String playerName, Pass pass) {
        super(playerName, pass);
    }


    public void recalculatePermissions() {
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

    public boolean inGroup(String world, String groupStr){
        if (world == null) return false;
        Group group = Groups.getGroup(groupStr);
        if (group == null) return false;
        Pass pass = getWorldPass(world);
        if (pass == null) return false;
        return pass.groups.contains(group);
    }

    private boolean useWorlds(){
        return MultipassPlugin.getCfg().enableWorldSupport;
    }

}
