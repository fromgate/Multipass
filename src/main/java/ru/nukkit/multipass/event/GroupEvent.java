package ru.nukkit.multipass.event;

import cn.nukkit.event.Cancellable;
import cn.nukkit.event.player.PlayerEvent;
import ru.nukkit.multipass.permissions.Group;
import ru.nukkit.multipass.permissions.Groups;

/**
 * Created by Igor on 11.05.2016.
 */
public class GroupEvent extends PlayerEvent implements Cancellable {
    String groupId;

    public String getGroupId() {
        return groupId;
    }

    public Group getGroup() {
        return Groups.getGroup(groupId);
    }


}
