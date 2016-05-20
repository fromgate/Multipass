package ru.nukkit.multipass.command.group;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import ru.nukkit.multipass.command.Cmd;
import ru.nukkit.multipass.command.CmdDefine;
import ru.nukkit.multipass.permissions.Groups;
import ru.nukkit.multipass.util.Message;

/**
 * Created by Igor on 07.05.2016.
 */
@CmdDefine(command = "group", alias = "groupperm", allowConsole = true, subCommands = {"remove|rmv|delete|del", "\\S+"}, permission = "multipass.admin", description = Message.CMD_GROUP_REMOVE)
public class GroupRemove extends Cmd {


    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        String id = args[1];
        if (!Groups.exist(id)) return Message.GROUP_NOTEXIST.print(sender, id);
        Groups.remove(id);
        return Message.GROUP_REMOVE_OK.print(sender, id);
    }
}
