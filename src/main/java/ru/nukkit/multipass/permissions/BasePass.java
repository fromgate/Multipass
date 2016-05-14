package ru.nukkit.multipass.permissions;

import java.util.*;

/**
 * Created by Igor on 14.05.2016.
 */
public abstract class BasePass extends Pass {

    protected String name;
    private Map<String,Pass> worldPass;

    public BasePass(String name) {
        super();
        this.worldPass = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    }


    public boolean hasWorldPass (String world){
        return worldPass.containsKey(world);
    }

    public Pass getWorldPass (String world){
        if (worldPass.containsKey(world)) return worldPass.get(world);
        return null;
    }



    public void remove (String perm){

    }

    public void remove(String world, String perm) {
        remove(world, new Permission(perm));
    }

    public void remove(String world, Permission permission) {
        if (!hasWorldPass(world)) return;
        Pass pass = worldPass.get(world);
        if (pass.permissions.contains(permission))
            pass.permissions.remove(permission);
    }

    public Collection<Permission> getPermissions(String world) {
        if (hasWorldPass(world)) return getWorldPass(world).permissions;
        return Collections.EMPTY_SET;
    }

    public void setPermissions(String world, Collection<Permission> permissions) {
        getWorldPassOrCreate(world).setPermissions(permissions);
    }

    public int getPriority(String world) {
        return worldPass.containsKey(world) ? worldPass.get(world).priority : 0;
    }


    public void setPriority(String world, int priority) {
        getWorldPassOrCreate(world).setPriority(priority);
    }

    public String getPrefix(String world) {
        return worldPass.containsKey(world) ? worldPass.get(world).prefix: "";
    }

    public void setPrefix(String world, String prefix) {
        getWorldPassOrCreate(world).setPrefix(prefix);
    }

    public String getSuffix(String world){
        return worldPass.containsKey(world) ? worldPass.get(world).suffix : "";
    }

    public void setSuffix(String world, String suffix) {
        getWorldPassOrCreate(world).setSuffix(suffix);
    }

    public void add(String world, String perm, boolean positive) {
        add(world, new Permission(perm, positive));
    }

    public void add(String world, String perm) {
        add(world, new Permission(perm));
    }

    public void add(String world, Permission permission) {
        getWorldPassOrCreate(world).setPermission(permission);
    }


    public List<String> getGroupList(String world) {
        return worldPass.containsKey(world) ? worldPass.get(world).getGroupList() : null;
    }

    public List<String> getPermissionList(String world) {
        return worldPass.containsKey(world) ? worldPass.get(world).getPermissionList() : null;
    }

    public void add(String world, Group group2) {
        getWorldPassOrCreate(world).addGroup(group2);
    }

    public void set(String world, Group group2) {
        getWorldPassOrCreate(world).addGroup(group2);
    }

    public boolean isPermissionSet(String world, String permStr) {
        if (!hasWorldPass(world)) return false;
        return getWorldPass(world).isPermissionSet(permStr);
    }

    public void setPermissionsList(String world, List<String> permissions) {
        getWorldPassOrCreate(world).setPermissionsList(permissions);
    }

    public void setGroupList(String world, List<String> groupList) {
        getWorldPassOrCreate(world).setGroupList(groupList);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasePass that = (BasePass) o;
        return name != null ? name.equalsIgnoreCase(that.name) : false;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public String getName() {
        return name;
    }

    private Pass getWorldPassOrCreate (String world){
        if (world == null||world.isEmpty()) return this;
        Pass pass = getWorldPass(world);
        if (pass == null) {
            pass = new Pass();
            this.worldPass.put(world,pass);
        }
        return pass;
    }

}
