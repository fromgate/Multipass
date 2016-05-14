package ru.nukkit.permiter;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.event.player.PlayerTeleportEvent;
import ru.nukkit.permiter.permissions.Users;

/**
 * Created by Igor on 01.05.2016.
 */
public class LeelooListener implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void onJoin(PlayerJoinEvent event) {
        Users.loadUser(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onQuit(PlayerQuitEvent event) {
        Users.closeUser(event.getPlayer());
    }

    public void onTeleport (PlayerTeleportEvent event){
        if (event.getFrom().getLevel().equals(event.getTo().getLevel())) return;
        Users.recalculatePermissions(event.getPlayer().getName());
    }

}
