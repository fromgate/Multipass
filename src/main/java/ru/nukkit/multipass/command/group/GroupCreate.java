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

@CmdDefine(command = "group", alias = "groupperm", allowConsole = true, subCommands = {"create|new", "\\S+"}, permission = "multipass.admin", description = Message.CMD_GROUP_CREATE)
public class GroupCreate extends Cmd {
    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        String id = args[1];
        if (Groups.exist(id)) return Message.GROUP_EXIST.print(sender, id);
        Groups.create(id);
        return Message.GROUP_CREATE_OK.print(sender, id);
    }
}
