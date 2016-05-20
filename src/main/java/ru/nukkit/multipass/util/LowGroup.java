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

import ru.nukkit.multipass.permissions.BasePass;

import java.util.Comparator;

/**
 * Created by Igor on 16.05.2016.
 */
public class LowGroup implements Comparator<BasePass> {
    @Override
    public int compare(BasePass o1, BasePass o2) {
        if (o1.getPriority()==o2.getPriority()) return 0;
        return o1.getPriority()>o2.getPriority() ? 1 : -1;
    }
}
