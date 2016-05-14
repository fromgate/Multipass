package ru.nukkit.multipass.command.group;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import ru.nukkit.multipass.command.Cmd;
import ru.nukkit.multipass.command.CmdDefine;
import ru.nukkit.multipass.permissions.Groups;
import ru.nukkit.multipass.util.Message;

/**
 * Created by Igor on 08.05.2016.
 */
@CmdDefine(command = "group", alias = "groupperm", allowConsole = true, subCommands = {"\\S+", "setsuffix|suffix|sx", "\\S+"}, permission = "multipass.admin", description = Message.CMD_GROUP_SETSUFFIX)
public class GroupSetSuffix extends Cmd {

    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        String id = args[0];
        if (!Groups.exist(id)) return Message.GROUP_SETSUFFIX_NOTEXIST.print(sender, id);
        String prefix = args[2];
        Groups.setPrefix(id, prefix);
        return Message.GROUP_SETSUFFIX_OK.print(sender, id, prefix);
    }
}
