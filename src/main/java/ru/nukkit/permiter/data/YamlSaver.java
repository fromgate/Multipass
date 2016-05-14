package ru.nukkit.permiter.data;

import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;
import ru.nukkit.permiter.MultipassPlugin;
import ru.nukkit.permiter.permissions.Group;
import ru.nukkit.permiter.permissions.User;
import ru.nukkit.permiter.util.Message;

import java.io.File;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Igor on 17.04.2016.
 */
public class YamlSaver implements DataSource {

    private File userDir;
    private File groupFile;
    private File worldFile;

    public YamlSaver() {
        this.userDir = new File(MultipassPlugin.getPlugin().getDataFolder() + File.separator + "users");
        this.userDir.mkdirs();
        this.groupFile = new File(MultipassPlugin.getPlugin().getDataFolder() + File.separator + "groups.yml");
        this.worldFile = new File(MultipassPlugin.getPlugin().getDataFolder() + File.separator + "worlds.yml");
    }

    private File getUserFile(String user) {
        return new File(userDir + File.separator + user.toLowerCase() + ".yml");
    }

    @Override
    public User loadUser(String playerName) {
        File file = getUserFile(playerName);
        User user = new User(playerName);
        Config cfg = new Config(Config.YAML);
        if (file.exists()) {
            cfg.load(file.toString());
            user.setGroupList(cfg.getStringList("groups"));
            user.setPermissionsList(cfg.getStringList("permissions"));
        }
        cfg.save(file);
        return user;
    }

    @Override
    public void saveUser(User user) {
        File file = getUserFile(user.getName());
        Message.debugMessage("Save user file: ", file.toString());
        if (user.getPermissionList().isEmpty() && user.getGroupList().isEmpty()) {
            if (file.exists()) file.delete();
            Message.debugMessage(user.getName(), "data is empty. File removed");
        } else {
            Config cfg = new Config(file, Config.YAML);
            cfg.set("groups", user.getGroupList());
            cfg.set("permissions", user.getPermissionList());
            Message.debugMessage("Saved user ", user.getName(), "Permissions:", user.getPermissionList().size(), "Groups:", user.getGroupList().size());
            cfg.save(file);
        }
    }

    /*

    @Override
    public boolean isStored(String userName) {
        File file = getUserFile(userName);
        return file.exists();
    } */

    @Override
    public void saveGroups(Collection<Group> groups) {
        Config cfg = new Config();
        groups.forEach(g -> {
            ConfigSection section = new ConfigSection();
            section.set("groups", g.getGroupList());
            section.set("permissions", g.getPermissionList());
            section.set("prefix", g.getPrefix());
            section.set("suffix", g.getSuffix());
            cfg.set(g.getName(), section);
        });
        cfg.save(this.groupFile);
    }

    @Override
    public Map<String, Group> loadGroups() {
        Map<String, Group> groups = new TreeMap<>();
        if (!groupFile.exists()) return groups;
        Config cfg = new Config(groupFile, Config.YAML);
        cfg.getSections().entrySet().forEach(e -> {
            Group group = new Group(e.getKey());
            ConfigSection section = (ConfigSection) e.getValue();
            group.setPermissionsList(section.getStringList("permissions"));
            group.setGroupList(section.getStringList("groups"));
            group.setPrefix(section.getString("prefix", ""));
            group.setSuffix(section.getString("suffix", ""));
            groups.put(e.getKey(), group);
        });
        Message.debugMessage(groups.size(), "groups loaded");
        return groups;
    }
}