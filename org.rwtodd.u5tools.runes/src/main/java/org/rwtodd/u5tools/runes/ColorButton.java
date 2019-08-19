/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rwtodd.u5tools.runes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;

/**
 * a button that displays a color, and activates a color chooser.
 * @author Richard Todd
 */
public final class ColorButton extends JButton {

    private Color current;

    public ColorButton(Color c) {
        setSelectedColor(c);
        super.addActionListener((e) -> {
            Color newColor = JColorChooser.showDialog(null, "Choose a color", current);
            setSelectedColor(newColor);
        });
    }

    public Color getSelectedColor() {
        return current;
    }

    public void setSelectedColor(Color newColor) {
        if (newColor == null) {
            return;
        }
        current = newColor;
        setIcon(createIcon(current, 16, 16));
        repaint();
    }

    public static ImageIcon createIcon(Color main, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, java.awt.image.BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(main);
        graphics.fillRect(0, 0, width, height);
        graphics.setXORMode(Color.DARK_GRAY);
        graphics.drawRect(0, 0, width - 1, height - 1);
        image.flush();
        ImageIcon icon = new ImageIcon(image);
        return icon;
    }

}
