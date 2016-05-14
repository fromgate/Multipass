package ru.nukkit.permiter.command.group;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import ru.nukkit.permiter.command.Cmd;
import ru.nukkit.permiter.command.CmdDefine;
import ru.nukkit.permiter.permissions.Groups;
import ru.nukkit.permiter.util.Message;

/**
 * Created by Igor on 07.05.2016.
 */

@CmdDefine(command = "group", alias = "groupperm", allowConsole = true, subCommands = {"\\S+", "addgroup|addgrp|agrp|ag", "\\S+"}, permission = "permiter.admin", description = Message.CMD_GROUP_ADDGROUP)
public class GroupAddGroup extends Cmd {


    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        String id1 = args[0];
        String id2 = args[2];
        if (!Groups.exist(id1)) Message.GROUP_ADDGROUP_NOTEXIST.print(sender, id1);
        if (!Groups.exist(id2)) Message.GROUP_ADDGROUP_NOTEXIST.print(sender, id2);
        Groups.addGroup(id1, id2);
        return Message.GROUP_ADDGROUP_OK.print(sender, id1, id2);
    }
}
