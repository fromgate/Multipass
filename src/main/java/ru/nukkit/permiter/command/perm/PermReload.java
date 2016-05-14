package ru.nukkit.permiter.command.perm;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import ru.nukkit.permiter.MultipassPlugin;
import ru.nukkit.permiter.command.Cmd;
import ru.nukkit.permiter.command.CmdDefine;
import ru.nukkit.permiter.permissions.Groups;
import ru.nukkit.permiter.permissions.Users;
import ru.nukkit.permiter.permissions.Worlds;
import ru.nukkit.permiter.util.Message;

/**
 * Created by Igor on 06.05.2016.
 */

@CmdDefine(command = "perm", allowConsole = true, subCommands = "reload", permission = "permiter.admin", description = Message.CMD_PERM_RELOAD)
public class PermReload extends Cmd {
    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        MultipassPlugin.getCfg().load();
        Worlds.load();
        Groups.loadGroups();
        Users.reloadUsers();
        return Message.PERM_RELOADED.print(sender);
    }
}
