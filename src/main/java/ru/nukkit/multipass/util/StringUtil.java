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

import java.util.Collection;

public class StringUtil {

    public static String join(Collection<String> collection) {
        return join(collection, ", ");
    }

    public static String join(Collection<String> collection, String divider) {
        if (divider == null) divider = " ";
        StringBuilder sb = new StringBuilder();
        for (String s : collection) {
            if (sb.length() > 0) sb.append(divider);
            sb.append(s);
        }
        return sb.toString();
    }
}