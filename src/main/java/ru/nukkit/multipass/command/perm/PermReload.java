package ru.nukkit.multipass.command.perm;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import ru.nukkit.multipass.MultipassPlugin;
import ru.nukkit.multipass.command.Cmd;
import ru.nukkit.multipass.command.CmdDefine;
import ru.nukkit.multipass.permissions.Groups;
import ru.nukkit.multipass.permissions.Users;
import ru.nukkit.multipass.util.Message;

/**
 * Created by Igor on 06.05.2016.
 */

@CmdDefine(command = "perm", allowConsole = true, subCommands = "reload", permission = "multipass.admin", description = Message.CMD_PERM_RELOAD)
public class PermReload extends Cmd {
    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        MultipassPlugin.getCfg().load();
        Groups.loadGroups();
        Users.reloadUsers();
        return Message.PERM_RELOADED.print(sender);
    }
}
