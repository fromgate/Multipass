package ru.nukkit.permiter.command.group;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import ru.nukkit.permiter.command.Cmd;
import ru.nukkit.permiter.command.CmdDefine;
import ru.nukkit.permiter.permissions.Groups;
import ru.nukkit.permiter.util.Message;

/**
 * Created by Igor on 08.05.2016.
 */

@CmdDefine(command = "group", alias = "groupperm", allowConsole = true, subCommands = {"\\S+", "setgroup|setgrp|sgrp|sg", "\\S+"}, permission = "permiter.admin", description = Message.CMD_GROUP_ADDGROUP)
public class GroupSetGroup extends Cmd {
    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        String id1 = args[0];
        String id2 = args[2];
        if (!Groups.exist(id1)) Message.GROUP_SETGROUP_NOTEXIST.print(sender, id1);
        if (!Groups.exist(id2)) Message.GROUP_SETGROUP_NOTEXIST.print(sender, id2);
        Groups.setGroup(id1, id2);
        return Message.GROUP_SETGROUP_OK.print(sender, id1, id2);

    }
}
