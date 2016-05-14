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

@CmdDefine(command = "group", alias = "groupperm", allowConsole = true, subCommands = {"\\S+", "removeperm|rmvperm|rp", "\\S+"}, permission = "permiter.admin", description = Message.CMD_GROUP_REMOVEPERM)
public class GroupRemovePerm extends Cmd {
    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        String id = args[0];
        if (!Groups.exist(id)) return Message.GROUP_REMOVEPERM_NOTEXIST.print(sender, id);
        String permStr = args[2];
        if (!Groups.isPermissionSet(id, permStr)) return Message.GROUP_REMOVEPERM_PERMUNSET.print(sender, id, permStr);
        Groups.setPerm(id, permStr);
        return Message.GROUP_REMOVEPERM_OK.print(sender, id, permStr);
    }
}
