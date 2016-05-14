package ru.nukkit.permiter.command.perm;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import ru.nukkit.permiter.command.Cmd;
import ru.nukkit.permiter.command.CmdDefine;
import ru.nukkit.permiter.command.Commander;
import ru.nukkit.permiter.util.Message;

/**
 * Created by Igor on 03.05.2016.
 */

/*

TODO
  + /perm help - помощь
  /perm reload - перезагрузить
  + /perm user <name> - показать информацию по пермишенам игрока
  + /perm group <name> - показать информацию по группе

  настройка игрока
  /user <name> setperm | removeperm | setgroup | addgroup | removegroup

  настройка группы
  + /group <id> create | remove

  + /group <id> setperm
  + removeperm
  + setgroup
  + addgroup
  + removegroup

  + /group <id> setprfix | setsuffix

  /world <id> setgroup <group>
  /world <id> 
  /world setworld <world>


 */

@CmdDefine(command = "perm", allowConsole = true, subCommands = "help", permission = "permiter.admin", description = Message.CMD_PERM_HELP)
public class PermHelp extends Cmd {

    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        int page = args.length == 2 && args[1].matches("\\d+") ? Integer.parseInt(args[1]) : 1;
        Commander.printHelp(sender, page);
        return true;
    }
}
