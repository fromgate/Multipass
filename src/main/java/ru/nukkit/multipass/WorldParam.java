package ru.nukkit.multipass;

/**
 * Created by Igor on 17.05.2016.
 */
public class WorldParam {
    public String world;
    public String param;


    public WorldParam (String param){
        world = null;
        this.param = param;
        if (param.contains(":")){
            String[] ln = param.split(":");
            world=ln[0];
            param=ln[1];
        }
    }

    public WorldParam (String[] args, int num){
        this.world = null;
        this.param = args[num];
        if (args.length>num){
            this.world = args[num];
            this.param = args[num+1];
        }
    }

}
