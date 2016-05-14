package ru.nukkit.multipass;

import cn.nukkit.plugin.PluginBase;
import ru.nukkit.multipass.command.Commander;
import ru.nukkit.multipass.data.DataProvider;
import ru.nukkit.multipass.permissions.Groups;
import ru.nukkit.multipass.util.Cfg;
import ru.nukkit.multipass.util.Message;

public class MultipassPlugin extends PluginBase {

    private static MultipassPlugin instance;
    private Cfg cfg;

    public static MultipassPlugin getPlugin() {
        return instance;
    }

    public static Cfg getCfg() {
        return instance.cfg;
    }


    @Override
    public void onEnable() {
        instance = this;
        cfg = new Cfg();
        cfg.load();
        cfg.save();
        getServer().getPluginManager().registerEvents(new LeelooListener(), this);
        Message.init(this);
        Commander.init(this);
        DataProvider.init();
        Groups.init();
    }

}
