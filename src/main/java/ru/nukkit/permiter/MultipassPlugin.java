package ru.nukkit.permiter;

import cn.nukkit.plugin.PluginBase;
import ru.nukkit.permiter.command.Commander;
import ru.nukkit.permiter.data.DataProvider;
import ru.nukkit.permiter.permissions.Groups;
import ru.nukkit.permiter.util.Cfg;
import ru.nukkit.permiter.util.Message;

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
