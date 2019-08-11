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
import java.awt.Shape;
import java.util.HashMap;

final class Alphabet extends HashMap<String, Shape> {

    private final double letterHeight;
    private final double letterWidth;
    
    Alphabet() {
        super(26);
        
        var s = new Path2D.Float();
        s.moveTo(0.0f, 10.0f); s.lineTo(0.0f, 0.0f); s.lineTo(5.0f,5.0f);
        s.moveTo(0.0f, 3.0f); s.lineTo(4.0f, 7.0f);
        put("A", s);

        s = new Path2D.Float();
        s.moveTo(0f,10f); s.lineTo(0f,0f); s.lineTo(3.5f,3f); 
        s.lineTo(2.5f,5f); s.lineTo(5f,7f); s.lineTo(0f,10f);
        s.lineTo(0f,0f);
        put("B", s);
        
        s = new Path2D.Float();
        s.moveTo(0f,10f); s.lineTo(1.5f,0f);
        s.moveTo(0.9f,4.8f); s.lineTo(5f,10f);
        put("C", s);
        
        s = new Path2D.Float();
        s.moveTo(0f,0f); s.lineTo(5f,10f); s.lineTo(5f,0f); 
        s.lineTo(0f,10f); s.lineTo(0f,0f); s.lineTo(5f,10f);
        put("D", s);
        
        s = new Path2D.Float();
        s.moveTo(0f,10f); s.lineTo(0.7f,0f); s.lineTo(2.5f,4f); 
        s.lineTo(4.3f,0f); s.lineTo(5f,10f);
        put("E", s);
        
        s = new Path2D.Float();
        put("F", s);
        
        s = new Path2D.Float();
        put("G", s);
        
        s = new Path2D.Float();
        put("H", s);
        
        s = new Path2D.Float();
        put("I", s);
        
        s = new Path2D.Float();
        put("J", s);
        
        s = new Path2D.Float();
        put("K", s);
        
        s = new Path2D.Float();
        put("L", s);
        
        s = new Path2D.Float();
        put("M", s);
        
        s = new Path2D.Float();
        put("N", s);
        
        s = new Path2D.Float();
        put("O", s);
        
        s = new Path2D.Float();
        put("P", s);
        
        s = new Path2D.Float();
        put("Q", s);
        
        s = new Path2D.Float();
        put("R", s);
        
        s = new Path2D.Float();
        put("S", s);
        
        s = new Path2D.Float();
        put("T", s);
        
        s = new Path2D.Float();
        put("U", s);
        
        s = new Path2D.Float();
        put("V", s);
        
        s = new Path2D.Float();
        put("W", s);
        
        s = new Path2D.Float();
        put("X", s);
        
        s = new Path2D.Float();
        put("Y", s);
        
        s = new Path2D.Float();
        put("Z", s);
        
        s = new Path2D.Float();
        s.moveTo(2f,5f); s.lineTo(2.5f,4f); s.lineTo(3f,5f); 
        s.lineTo(2.5f,6f); s.lineTo(2f,5f); s.lineTo(2.5f,4f);
        put("space", s);

        s = new Path2D.Float();
        put("TH", s);

        s = new Path2D.Float();
        put("NG", s);

        s = new Path2D.Float();
        put("EA", s);

        s = new Path2D.Float();
        put("ST", s);

        s = new Path2D.Float();
        put("EE", s);
        
        // calculate the max letter height...
        letterHeight = values().stream()
                .mapToDouble((l) -> l.getBounds2D().getHeight())
                .max()
                .getAsDouble();
        // calculate the max letter width...
        letterWidth = values().stream()
                .mapToDouble((l) -> l.getBounds2D().getWidth())
                .max()
                .getAsDouble();
    }

    double getLetterHeight() { return letterHeight; }
    double getLetterWidth() { return letterWidth; }
}

