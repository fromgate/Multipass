/*
    Multipass, Nukkit permissions plugin
    Copyright (C) 2016  fromgate, nukkit.ru

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package ru.nukkit.multipass.command.user;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import ru.nukkit.multipass.WorldParam;
import ru.nukkit.multipass.command.Cmd;
import ru.nukkit.multipass.command.CmdDefine;
import ru.nukkit.multipass.permissions.Users;
import ru.nukkit.multipass.util.Message;

@CmdDefine(command = "user", alias = "userperm", allowConsole = true, subCommands = {"\\S+", "removegroup|rmvgrp|rgrp|rg", "\\S+"}, permission = "multipass.admin", description = Message.CMD_USER_REMOVEGROUP)
public class UserRemoveGroup extends Cmd {

    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        String userName = args[0];
        if (!Users.isRegistered(userName)) return Message.USER_REMOVEGROUP_NOTFOUND.print(sender, userName);
        WorldParam wp = new WorldParam(args[2]);

        if (!Users.inGroup(userName, wp)) return
                wp.message(Message.USER_REMOVEGROUP_NOTSET, Message.USER_REMOVEGROUPW_NOTSET)
                        .print(sender, wp.param, userName,wp.world);
        Users.removeGroup(userName, wp);
        wp.message(Message.USER_REMOVEGROUP_OK_INFORM,Message.USER_REMOVEGROUPW_OK_INFORM)
                .print(Server.getInstance().getPlayerExact(userName),wp.param,wp.world);
        return wp.message(Message.USER_REMOVEGROUP_OK,Message.USER_REMOVEGROUPW_OK).print(sender, wp.param, userName, wp.world);
    }
}
