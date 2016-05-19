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

@CmdDefine(command = "user", alias = "userperm", allowConsole = true, subCommands = {"\\S+", "removegroup|rmvgrp|rgrp|rg", "\\S+"}, permission = "multipass.admin", description = Message.CMD_USER_REMOVEGROUP)
public class  UserRemovegroup extends Cmd {

    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        String userName = args[0];
        if (!Users.isRegistered(userName)) return Message.USER_REMOVEGROUP_NOTFOUND.print(sender, userName);
        String permStr = args[2];
        if (!Users.inGroup(userName, permStr)) return Message.USER_REMOVEGROUP_NOTSET.print(sender, permStr, userName);
        Users.removeGroup(userName, permStr);
        return Message.USER_REMOVEPERM_OK.print(sender, permStr, userName);
    }
}
