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

package ru.nukkit.multipass.util;

import ru.nukkit.multipass.permissions.BaseNode;

import java.util.Comparator;

public class LowBase implements Comparator<BaseNode> {
    @Override
    public int compare(BaseNode o1, BaseNode o2) {
        if (o1.equals(o2)) return 0;
        //if (o1.getPriority()==o2.getPriority()) return 0;
        return o1.getPriority()>o2.getPriority() ? 1 : -1;

        /*
        if (o1.getPriority()==o2.getPriority()) return 0;
        return o1.getPriority()>o2.getPriority() ? 1 : -1;
        */
    }
}
