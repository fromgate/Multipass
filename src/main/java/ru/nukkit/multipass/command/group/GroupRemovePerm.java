package ru.nukkit.multipass.command.group;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import ru.nukkit.multipass.WorldParam;
import ru.nukkit.multipass.command.Cmd;
import ru.nukkit.multipass.command.CmdDefine;
import ru.nukkit.multipass.permissions.Groups;
import ru.nukkit.multipass.util.Message;

/**
 * Created by Igor on 08.05.2016.
 */

@CmdDefine(command = "group", alias = "groupperm", allowConsole = true, subCommands = {"\\S+", "removeperm|rmvperm|rp", "\\S+"}, permission = "multipass.admin", description = Message.CMD_GROUP_REMOVEPERM)
public class GroupRemovePerm extends Cmd {
    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        String id = args[0];
        if (!Groups.exist(id)) return Message.GROUP_REMOVEPERM_NOTEXIST.print(sender, id);
        String permStr = args[2];
        WorldParam wp = new WorldParam(args,2);

        if (!Groups.isPermissionSet(id, permStr)) return Message.GROUP_REMOVEPERM_PERMUNSET.print(sender, id, permStr);


        Groups.removePermission (id, permStr);
        return Message.GROUP_REMOVEPERM_OK.print(sender, id, permStr);
    }
}
