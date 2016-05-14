package ru.nukkit.permiter.command.user;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import ru.nukkit.permiter.command.Cmd;
import ru.nukkit.permiter.command.CmdDefine;
import ru.nukkit.permiter.permissions.Users;
import ru.nukkit.permiter.util.Message;

/**
 * Created by Igor on 06.05.2016.
 */

@CmdDefine(command = "user", alias = "userperm", allowConsole = true, subCommands = {"\\S+", "setperm|sperm|sp|addperm|aperm|ap", "\\S+"}, permission = "permiter.admin", description = Message.CMD_USER_SETPERM)
public class UserSetperm extends Cmd {
    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        Users.setPermission(args[0], args[2]);
        return Message.USER_SETPERM_OK.print(sender, args[0], args[2]);
    }
}
