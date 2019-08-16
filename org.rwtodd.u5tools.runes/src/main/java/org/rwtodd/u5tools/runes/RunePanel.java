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

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.util.Arrays;
import javax.swing.JPanel;

/**
 * Draws the actual text on screen
 *
 * @author Richard Todd
 */
class RunePanel extends JPanel {

    private Shape letters;
    private BufferedImage runeImage;
    private BufferedImage shadowImage;

    private float spacingX;
    private float spacingY;
    private final Alphabet alphabet;
    private Color shadowColor;
    private double shadowOffsetX;
    private double shadowOffsetY;
    private int paddingX;
    private int paddingY;
    private ConvolveOp blurOp;

    RunePanel() {
        super();
        letters = new Path2D.Float();
        spacingX = 2f;
        spacingY = 2f;
        alphabet = new Alphabet();
        setForeground(Color.ORANGE);
        setBackground(Color.GREEN);
        shadowColor = Color.BLACK;
        shadowOffsetX = 0.5;
        shadowOffsetY = 0.5;
        paddingX = (int) alphabet.getLetterWidth();
        paddingY = (int) alphabet.getLetterHeight();
        runeImage = null;
        shadowImage = null;

        setupBlur(6);
    }

    private void setupBlur(int size) {
        // now blur...
        int total = size * size;
        float[] matrix = new float[total];
        for (int i = 0; i < total; i++) {
            matrix[i] = 1.0f / total;
        }
        blurOp = new ConvolveOp(new java.awt.image.Kernel(size, size, matrix),
                ConvolveOp.EDGE_ZERO_FILL,
                null);
    }

    /**
     * converts a line of text into a composite Shape.
     *
     * @param line the text to convert
     * @return a Shape of runes for the line
     */
    private Shape lineToShape(String line) {
        final var space = alphabet.get("space");
        final var result = new Path2D.Float();
        boolean spacePending = false;

        line = line.toUpperCase();
        final var llen = line.length();

        int idx = 0;
        double xlevel = 0;
        while (idx < llen) {
            // try the next two chars, then just the next char.. is it a rune?
            final var glyphStr = line.substring(idx, Math.min(llen, idx + 2));
            var glyph = alphabet.get(glyphStr);
            if (glyph == null) {
                glyph = alphabet.get(glyphStr.substring(0, 1));
                if (glyph == null) {
                    glyph = space;
                }
                ++idx;
            } else {
                idx += 2;
            }

            // if this is a space, and not at the beginning of the line,
            // just remember it.
            if (glyph == space) {
                if (xlevel > 0.0) {
                    spacePending = true;
                }
                continue;
            }

            // ok, add the glyph...
            if (spacePending) {
                spacePending = false;
                result.append(
                        space.getPathIterator(
                                AffineTransform.getTranslateInstance(xlevel, 0.0)),
                        false);
                xlevel += space.getRuneWidth() + spacingX;
            }

            result.append(
                    glyph.getPathIterator(
                            AffineTransform.getTranslateInstance(xlevel, 0.0)),
                    false);
            xlevel += glyph.getRuneWidth() + spacingX;
        }

        return result;
    }

    /**
     * render a multi-line string to runes. This method converts all of the text
     * into a large composite {@code Path2D} that contains the runes.
     *
     * @param txt the lines of text
     * @return the rendered shape
     */
    Shape render(String txt) {
        Shape[] lines = txt.lines().map(this::lineToShape).toArray(Shape[]::new);
        final double maxWidth = Arrays.stream(lines)
                .mapToDouble((s) -> s.getBounds2D().getMaxX())
                .max()
                .orElse(1.0);
        final var answer = new Path2D.Float();
        double ylevel = 0.0;
        for (final var s : lines) {
            answer.append(s.getPathIterator(
                    AffineTransform.getTranslateInstance(
                            (maxWidth - s.getBounds2D().getMaxX()) / 2.0,
                            ylevel)),
                    false);
            ylevel += alphabet.getLetterHeight() + spacingY;
        }
        return answer;
    }

    /**
     * renders given text as runes, and redraws the panel.
     *
     * @param txt the text to render
     */
    void drawText(String txt) {
        letters = render(txt);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        final var shapeSize = letters.getBounds2D();
        final var panelX = getWidth();
        final var panelY = getHeight();
        final var shapeX = shapeSize.getMaxX();
        final var shapeY = shapeSize.getMaxY();
        final var scaleFactor = Math.min(
                panelX / (shapeX + paddingX),
                panelY / (shapeY + paddingY)); // maintain aspect ratio

        if ((runeImage == null) || (runeImage.getWidth() != panelX)
                || (runeImage.getHeight() != panelY)) {
            runeImage = getGraphicsConfiguration()
                    .createCompatibleImage(panelX, panelY, Transparency.TRANSLUCENT);
            shadowImage = null;
        }

        var g2 = (Graphics2D) runeImage.getGraphics();
        // clear the rune image
        g2.setComposite(AlphaComposite.Clear);
        g2.fillRect(0, 0, panelX, panelY);
        g2.setComposite(AlphaComposite.SrcOver);
        
        // draw the letters centered
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.translate(
                (panelX - shapeX * scaleFactor) / 2.0,
                (panelY - shapeY * scaleFactor) / 2.0);
        g2.scale(scaleFactor, scaleFactor);
        g2.setColor(getForeground());
        g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
        g2.draw(letters);
        g2.dispose();

        // create the drop shadow
        shadowImage = blurOp.filter(runeImage, shadowImage);
        g2 = (Graphics2D) shadowImage.getGraphics();
        g2.setColor(shadowColor);
        g2.setComposite(AlphaComposite.SrcIn);
        g2.fillRect(0, 0, panelX, panelY);
        g2.dispose();

        // now paint the background, shadow, and image, in z-order
        g.setColor(getBackground());
        g.fillRect(0, 0, panelX, panelY);
        g.drawImage(shadowImage,
                (int) (shadowOffsetX * scaleFactor),
                (int) (shadowOffsetY * scaleFactor),
                null);
        g.drawImage(runeImage, 0, 0, null);
    }

}
