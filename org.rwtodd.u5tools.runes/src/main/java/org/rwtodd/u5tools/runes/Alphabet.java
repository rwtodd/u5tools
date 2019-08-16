/*
 * Copyright (C) 2019 Richard Todd
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package org.rwtodd.u5tools.runes;

import java.awt.geom.Path2D;
import java.util.HashMap;

final class Alphabet extends HashMap<String, Alphabet.Rune> {

    class Rune extends Path2D.Float {

        private final float pathWidth;

        Rune(float width) {
            super();
            pathWidth = width;
        }

        Rune() {
            this(5.5f);
        }

        float getRuneWidth() {
            return pathWidth;
        }

        float getRuneHeight() {
            return 10f;
        }
    }

    private final double letterHeight;
    private final double letterWidth;

    Alphabet() {
        super(26);

        var s = new Rune();
        s.moveTo(0.0f, 10.0f);
        s.lineTo(0.0f, 0.0f);
        s.lineTo(5.0f, 5.0f);
        s.moveTo(0.0f, 3.0f);
        s.lineTo(4.0f, 7.0f);
        put("A", s);

        s = new Rune();
        s.moveTo(0f, 10f);
        s.lineTo(0f, 0f);
        s.lineTo(3.5f, 3f);
        s.lineTo(2.5f, 5f);
        s.lineTo(5f, 7f);
        s.lineTo(0f, 10f);
        s.lineTo(0f, 0f);
        put("B", s);

        s = new Rune();
        s.moveTo(0f, 10f);
        s.lineTo(1.5f, 0f);
        s.moveTo(0.9f, 4.8f);
        s.lineTo(5f, 10f);
        put("C", s);

        s = new Rune(6f);
        s.moveTo(0f, 0f);
        s.lineTo(5f, 10f);
        s.lineTo(5f, 0f);
        s.lineTo(0f, 10f);
        s.lineTo(0f, 0f);
        s.lineTo(5f, 10f);
        put("D", s);

        s = new Rune();
        s.moveTo(0f, 10f);
        s.lineTo(0.7f, 0f);
        s.lineTo(2.5f, 4f);
        s.lineTo(4.3f, 0f);
        s.lineTo(5f, 10f);
        put("E", s);

        s = new Rune();
        s.moveTo(0f, 10f);
        s.lineTo(0f, 0f);
        s.moveTo(0f, 6.5f);
        s.lineTo(3f, 6.5f);
        s.lineTo(5f, 4.5f);
        s.lineTo(5f, 0f);
        s.moveTo(0f, 4f);
        s.lineTo(1.5f, 4f);
        s.lineTo(2.5f, 3f);
        s.lineTo(2.5f, 0f);
        put("F", s);

        s = new Rune();
//g
        s.moveTo(0f, 10f);
        s.lineTo(5f, 0f);
        s.moveTo(0f, 0f);
        s.lineTo(5f, 10f);
        put("G", s);

        s = new Rune(4.5f);
//h 
        s.moveTo(0f, 10f);
        s.lineTo(0f, 0f);
        s.lineTo(4.5f, 3f);
        s.lineTo(0f, 7f);
        put("H", s);

        s = new Rune(1.0f);
//rune-i
        s.moveTo(0f, 0f);
        s.lineTo(0f, 10f);
        put("I", s);

        s = new Rune();
//rune-j
        s.moveTo(2.5f, 10f);
        s.lineTo(2.5f, 0f);
        s.moveTo(2.5f, 0f);
        s.lineTo(5f, 3f);
        s.lineTo(2.5f, 6.5f);
        s.lineTo(0f, 3f);
        s.lineTo(2.5f, 0f);
        s.lineTo(5f, 3f);
        put("J", s);

        s = new Rune();
//rune-k
        s.moveTo(0f, 10f);
        s.lineTo(1.5f, 0f);
        s.moveTo(1.1f, 4.8f);
        s.lineTo(3f, 5.5f);
        s.lineTo(5f, 10f);
        put("K", s);

        s = new Rune(3.5f);
//rune-l
        s.moveTo(0f, 10f);
        s.lineTo(0f, 0f);
        s.lineTo(3f, 5f);
        put("L", s);

        s = new Rune();
//rune-m
        s.moveTo(0f, 10f);
        s.lineTo(0.7f, 0f);
        s.lineTo(4.7f, 7f);
        s.moveTo(5f, 10f);
        s.lineTo(4.3f, 0f);
        s.lineTo(0.3f, 7f);
        put("M", s);

        s = new Rune();
//rune-n
        s.moveTo(2.5f, 10f);
        s.lineTo(2.5f, 0f);
        s.moveTo(0f, 4f);
        s.lineTo(5f, 7f);
        put("N", s);

        s = new Rune();
//rune-o
        s.moveTo(0f, 10f);
        s.lineTo(0f, 0f);
        s.lineTo(2.5f, 3f);
        s.lineTo(5f, 0f);
        s.moveTo(0f, 3f);
        s.lineTo(2.5f, 6f);
        s.lineTo(5f, 3f);
        put("O", s);

        s = new Rune();
//rune-p
        s.moveTo(0f, 10f);
        s.lineTo(0f, 0f);
        s.moveTo(0f, 4.5f);
        s.lineTo(2.5f, 4.5f);
        s.lineTo(3.5f, 3.5f);
        s.lineTo(5f, 0f);
        s.moveTo(0f, 6f);
        s.lineTo(2.5f, 6.5f);
        s.lineTo(3.5f, 7.5f);
        s.lineTo(5f, 10f);
        put("P", s);

        s = new Rune();
//rune-q
        s.moveTo(0f, 10f);
        s.lineTo(0f, 0f);
        s.moveTo(0f, 4.5f);
        s.lineTo(2.5f, 4.5f);
        s.lineTo(3.5f, 3.5f);
        s.lineTo(5f, 0f);
        put("Q", s);

        s = new Rune();
//rune-r
        s.moveTo(0f, 10f);
        s.lineTo(0f, 0f);
        s.lineTo(4.2f, 3.5f);
        s.lineTo(1.5f, 6f);
        s.lineTo(5f, 10f);
        put("R", s);

        s = new Rune();
//rune-s
        s.moveTo(0f, 0f);
        s.lineTo(0f, 6f);
        s.lineTo(5f, 3.5f);
        s.lineTo(5f, 10f);
        put("S", s);

        s = new Rune();
//rune-t
        s.moveTo(2.5f, 0f);
        s.lineTo(2.5f, 10f);
        s.moveTo(0f, 3.5f);
        s.lineTo(2.5f, 0f);
        s.lineTo(5f, 3.5f);
        put("T", s);

        s = new Rune();
//rune-u
        s.moveTo(0f, 10f);
        s.lineTo(0.7f, 0f);
        s.lineTo(4.5f, 3.5f);
        s.lineTo(5f, 10f);
        put("U", s);

        s = new Rune();
//rune-v
        s.moveTo(0f, 10f);
        s.lineTo(2.5f, 0f);
        s.lineTo(5f, 10f);
        put("V", s);

        s = new Rune(6f);
//rune-w
        s.moveTo(0f, 10f);
        s.lineTo(0f, 0f);
        s.moveTo(5f, 10f);
        s.lineTo(5f, 0f);
        s.moveTo(0f, 2f);
        s.lineTo(5f, 6f);
        s.moveTo(0f, 4f);
        s.lineTo(5f, 8f);
        put("W", s);

        s = new Rune();
//rune-x
        s.moveTo(2.5f, 0f);
        s.lineTo(2.5f, 10f);
        s.moveTo(0f, 10f);
        s.lineTo(2.5f, 7f);
        s.lineTo(5f, 10f);
        put("X", s);

        s = new Rune();
//rune-y
        s.moveTo(0f, 10f);
        s.lineTo(0.7f, 0f);
        s.lineTo(4.5f, 3.5f);
        s.lineTo(5f, 10f);
        s.moveTo(0.2f, 3f);
        s.lineTo(4.8f, 6f);
        s.moveTo(2.5f, 4.5f);
        s.lineTo(2.5f, 10f);
        put("Y", s);

        s = new Rune();
//rune-z
        s.moveTo(2.5f, 0f);
        s.lineTo(2.5f, 10f);
        s.moveTo(0f, 0f);
        s.lineTo(2.5f, 4.5f);
        s.lineTo(5f, 0f);
        put("Z", s);

        s = new Rune(5f);
        s.moveTo(2f, 5f);
        s.lineTo(2.5f, 4f);
        s.lineTo(3f, 5f);
        s.lineTo(2.5f, 6f);
        s.lineTo(2f, 5f);
        s.lineTo(2.5f, 4f);
        put("space", s);

        s = new Rune(4.5f);
//rune-th
        s.moveTo(0f, 10f);
        s.lineTo(0f, 0f);
        s.moveTo(0f, 2.5f);
        s.lineTo(4.5f, 5f);
        s.lineTo(0f, 7.5f);
        put("TH", s);

        s = new Rune();
//rune-ng
        s.moveTo(0f, 10f);
        s.lineTo(4.5f, 4f);
        s.lineTo(2.5f, 0f);
        s.lineTo(0f, 4f);
        s.lineTo(4.5f, 10f);
        put("NG", s);

        s = new Rune(7f);
//rune-ea
        s.moveTo(3.5f, 0f);
        s.lineTo(3.5f, 10f);
        s.moveTo(0f, 0f);
        s.lineTo(1.75f, 3.5f);
        s.lineTo(3.5f, 0f);
        s.lineTo(5.25f, 3.5f);
        s.lineTo(7f, 0f);
        put("EA", s);

        s = new Rune(6f);
//rune-st
        s.moveTo(0f, 0f);
        s.lineTo(0f, 10f);
        s.lineTo(2.5f, 7f);
        s.lineTo(5f, 10f);
        s.lineTo(5f, 0f);
        s.lineTo(2.5f, 3f);
        s.lineTo(0f, 0f);
        s.lineTo(0f, 8f);
        put("ST", s);

        s = new Rune();
//rune-ee
        s.moveTo(0f, 10f);
        s.lineTo(5f, 5f);
        s.lineTo(0f, 0f);
        s.moveTo(5f, 10f);
        s.lineTo(0f, 5f);
        s.lineTo(5f, 0f);
        put("EE", s);

        // calculate the max letter height...
        letterHeight = values().stream()
                .mapToDouble((l) -> l.getRuneHeight())
                .max()
                .getAsDouble();

        // calculate the max letter width...
        letterWidth = values().stream()
                .mapToDouble((l) -> l.getRuneWidth())
                .max()
                .getAsDouble();
    }

    double getLetterHeight() {
        return letterHeight;
    }

    double getLetterWidth() {
        return letterWidth;
    }
}
