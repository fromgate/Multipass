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

import ru.nukkit.multipass.MultipassPlugin;

public class Group extends BasePass {

    public Group(String name, Pass pass) {
        super(name,pass);
    }

    public Group(String name) {
        super(name);
    }


    public boolean isDefault() {
        if (MultipassPlugin.getCfg().defaultGroup==null||MultipassPlugin.getCfg().defaultGroup.isEmpty()) return false;
        return this.getName().equalsIgnoreCase(MultipassPlugin.getCfg().defaultGroup);
    }
}