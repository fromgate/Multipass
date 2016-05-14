package ru.nukkit.permiter.util;

import cn.nukkit.utils.SimpleConfig;
import ru.nukkit.permiter.MultipassPlugin;

public class Cfg extends SimpleConfig {

    @Path("general.language")
    public String language = "default";

    @Path("general.language-save")
    public boolean saveLanguage = true;

    @Path("general.debug")
    public boolean debugMode = true;

    // Global or per-world permissions
    @Path("permissions.default-group")
    public String defaultGroup = "default";

    @Path("permissions.multiworld")
    public boolean enableWorldSupport = false;





    public Cfg() {
        super(MultipassPlugin.getPlugin());
    }
}
