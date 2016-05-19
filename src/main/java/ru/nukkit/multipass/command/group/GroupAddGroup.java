package ru.nukkit.multipass.command.group;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import ru.nukkit.multipass.WorldParam;
import ru.nukkit.multipass.command.Cmd;
import ru.nukkit.multipass.command.CmdDefine;
import ru.nukkit.multipass.permissions.Groups;
import ru.nukkit.multipass.util.Message;

/**
 * Created by Igor on 07.05.2016.
 */

@CmdDefine(command = "group", alias = "groupperm", allowConsole = true, subCommands = {"\\S+", "addgroup|addgrp|agrp|ag", "\\S+"}, permission = "multipass.admin", description = Message.CMD_GROUP_ADDGROUP)
public class GroupAddGroup extends Cmd {


    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        String id1 = args[0];
        WorldParam wp = new WorldParam(args,2);
        String id2 = args[2];
        if (!Groups.exist(id1)) Message.GROUP_ADDGROUP_NOTEXIST.print(sender, id1);
        if (!Groups.exist(id2)) Message.GROUP_ADDGROUP_NOTEXIST.print(sender, id2);
        Groups.addGroup(id1, wp);
        Message m = wp.world==null ? Message.GROUP_ADDGROUP_OK : Message.GROUP_ADDGROUPW_OK;
        return m.print(sender, id1, wp.param,wp.world);
    }
}
