package ru.nukkit.multipass.command.user;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import ru.nukkit.multipass.command.Cmd;
import ru.nukkit.multipass.command.CmdDefine;
import ru.nukkit.multipass.permissions.Users;
import ru.nukkit.multipass.util.Message;

/**
 * Created by Igor on 06.05.2016.
 */

@CmdDefine(command = "user", alias = "userperm", allowConsole = true, subCommands = {"\\S+", "setgroup|setgrp|sgrp|sg", "\\S+"}, permission = "multipass.admin", description = Message.CMD_USER_SETGROUP)
public class UserSetgroup extends Cmd {
    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        Users.setGroup(args[0], args[2]);
        return Message.USER_SETGROUP_OK.print(sender, args[0], args[1]);
    }
}
