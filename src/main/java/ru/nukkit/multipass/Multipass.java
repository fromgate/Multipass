package ru.nukkit.multipass;

import cn.nukkit.Player;

import java.util.List;
import java.util.Map;

/**
 * Created by Igor on 12.05.2016.
 */
public class Multipass {

    public static List<String> getGroups(Player player) {
        return null;
    }

    public static Map<String,Integer> getPrefixes (Player player){
        return null;
    }

    public static Map<String,Integer> getSuffixes (Player player){
        return null;
    }

    public static String getPrefix(Player player){
        return null;
    }

    public static String getSuffix(Player player){
        return null;
    }

    public boolean isInGroup(Player player){
        return false;
    }

    public boolean setPermission (Player player, String permission){
        return false;
    }

    public void setGroup (Player player, String group){

    }

    public void removePermission (Player player, String permission){

    }

    public void removeGroup (Player player, String group){

    }

    public void setPlayerPrefix (Player player, String prefix, String suffix, int priority){

    }

    public void setGroupPrefix (String group, String prefix, int priority){

    }


}
