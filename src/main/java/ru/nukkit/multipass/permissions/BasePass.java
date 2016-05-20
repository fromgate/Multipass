/*
    Multipass, Nukkit permissions plugin
    Copyright (C) 2016  fromgate, nukkit.ru

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package ru.nukkit.multipass.permissions;

import ru.nukkit.multipass.util.HighBase;

import java.util.*;

import static com.sun.javafx.fxml.expression.Expression.add;

public abstract class BasePass extends Pass {

    protected String name;
    private Map<String,Pass> worldPass;

    public BasePass(String name) {
        super();
        this.name = name;
        this.worldPass = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    }

    public BasePass(String playerName, Pass pass) {
        super (pass);
        this.name = playerName;
        this.worldPass = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    }

    public Set<Group> getAllGroups(){
        Set<Group> algroups = new TreeSet<>(new HighBase());
        groups.forEach(g -> {
            if (!algroups.contains(g)) {
                algroups.add(g);
                algroups.addAll(g.getAllGroups());
            }
        });
        return algroups;
    }

    public Set<BasePass> getAllPasses(){
        Set<BasePass> passes = new TreeSet<>(new HighBase());
        passes.add(this);
        groups.forEach(p -> {
            if (!passes.contains(p)) {
                passes.add(p);
                passes.addAll(p.getAllPasses());
            }
        });
        return passes;
    }

    public Collection<Permission> getPermissions(String world) {
        return hasWorldPass(world) ? getWorldPass(world).getPermissions() : Collections.EMPTY_SET;
    }

    public void setPermissions(String world, Collection<Permission> permissions) {
        getWorldPassOrCreate(world).setPermissions(permissions);
    }

    public int getPriority(String world) {
        return hasWorldPass(world) ? getWorldPass(world).getPriority() : 0;
    }

    public void setPriority(String world, int priority) {
        getWorldPassOrCreate(world).setPriority(priority);
    }

    public String getPrefix(String world) {
        return hasWorldPass(world) ? getWorldPass(world).getPrefix() : "";
    }

    public void setPrefix(String world, String prefix) {
        getWorldPassOrCreate(world).setPrefix(prefix);
    }

    public String getSuffix(String world){
        return hasWorldPass(world) ? getWorldPass(world).getSuffix() : "";
    }

    public void setSuffix(String world, String suffix) {
        getWorldPassOrCreate(world).setSuffix(suffix);
    }

    public void addPermission(String world, String perm, boolean positive) {
        add(world, new Permission(perm, positive));
    }

    public void addPermission(String world, String perm) {
        add(world, new Permission(perm));
    }

    public void addPermission(String world, Permission permission) {
        getWorldPassOrCreate(world).setPermission(permission);
    }


    public List<String> getGroupList(String world) {
        return hasWorldPass(world) ? getWorldPass(world).getGroupList() : Collections.EMPTY_LIST;
    }

    public List<String> getPermissionList(String world) {
        return hasWorldPass(world) ? getWorldPass(world).getPermissionList() : Collections.EMPTY_LIST;
    }

    public void addGroup(String world, String group) {
        addGroup (world, Groups.getGroup(group));
    }

    public void addGroup(String world, Group group) {
        if (group==null) return;
        getWorldPassOrCreate(world).addGroup(group);
    }

    /*
    public void set(String world, Group group2) {
        getWorldPassOrCreate(world).addGroup(group2);
    } */

    public void setGroup (String world, String groupStr){
        setGroup(world, Groups.getGroup(world));
    }

    public void setGroup(String world, Group group){
        if (group==null) return;
       getWorldPassOrCreate(world).setGroup(group);
    }


    public boolean isPermissionSet(String world, String permStr) {
        return hasWorldPass(world) ? getWorldPass(world).isPermissionSet(permStr) : false;
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

    public Pass getWorldPassOrCreate (String world){
        if (world == null||world.isEmpty()) return this;
        Pass pass = getWorldPass(world);
        if (pass == null) {
            pass = new Pass();
            this.worldPass.put(world,pass);
        }
        return pass;
    }

    public boolean hasWorldPass (String world){
        return getWorldPass(world)!=null;
    }

    public Pass getWorldPass (String world){
        return world!=null&&!world.isEmpty()&&worldPass.containsKey(world) ?  worldPass.get(world) : null;
    }

    public Map<String,Pass> getWorldPass(){
        return worldPass;
    }


    public void setWorldPass(String world, Pass pass) {
        this.worldPass.put(world,pass);
    }

    public void setPermission(String world, String perm) {
        setPermission(world, new Permission(perm));
    }

    public void setPermission(String world, Permission permission) {
        getWorldPassOrCreate(world).setPermission(permission);
    }

    public void removePermission (String world, String perm){
        removePermission(world, new Permission(perm));
    }

    public void removePermission (String world, Permission permission){
        if (hasWorldPass(world)) return;
        getWorldPass(world).removePermission(permission);
    }


    public void removeGroup(String world, String group) {
        if (hasWorldPass(world)) return;
        getWorldPass(world).removeGroup(group);
    }

    public void removeGroup(String world, Group group) {
        if (hasWorldPass(world)) return;
        getWorldPass(world).removeGroup(group);
    }
}
