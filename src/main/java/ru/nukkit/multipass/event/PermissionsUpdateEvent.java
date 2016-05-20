package ru.nukkit.multipass.event;

import cn.nukkit.event.Event;
import cn.nukkit.event.HandlerList;

/**
 * Created by Igor on 20.05.2016.
 */
public class PermissionsUpdateEvent extends Event {

    private String user;

    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlers() {
        return handlers;
    }


    public PermissionsUpdateEvent(){
        this.user = null;
    }

    public PermissionsUpdateEvent(String userName){
        this.user = userName;
    }


    /**
     * Get user, which permissions was changed
     * @return
     */
    public String getUser(){
        return this.user;
    }


    /**
     *
     * @return
     */
    public boolean isMassUpdate(){
        return user != null;
    }






}
