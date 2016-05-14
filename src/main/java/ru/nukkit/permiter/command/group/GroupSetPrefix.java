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

@CmdDefine(command = "group", alias = "groupperm", allowConsole = true, subCommands = {"\\S+", "setprefix|prefix|px", "\\S+"}, permission = "permiter.admin", description = Message.CMD_GROUP_SETPREFIX)
public class GroupSetPrefix extends Cmd {

    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        String id = args[0];
        if (!Groups.exist(id)) return Message.GROUP_SETPREFIX_NOTEXIST.print(sender, id);
        String prefix = args[2];
        Groups.setPrefix(id, prefix);
        return Message.GROUP_SETPREFIX_OK.print(sender, id, prefix);
    }
}
