/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rwtodd.u5tools.runes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 * a button that displays a color, and activates a color chooser.
 *
 * @author Richard Todd
 */
public final class ColorButton extends JButton implements ActionListener {

    private Color current;
    private final JColorChooser colorChooser;
    private final JDialog dialog;

    private static class PreviewLabel extends JPanel {

        PreviewLabel() {
            super();
            setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 40, 15, 40));
        }
        
        @Override
        public void setForeground(Color c) {
            super.setForeground(c);
            setBackground(c);
        }
    }

    public ColorButton(Color c) {
        super();
        setSelectedColor(c);
        colorChooser = new JColorChooser(current);
        colorChooser.setPreviewPanel(new PreviewLabel());
        dialog = JColorChooser.createDialog(this, "Choose a color", true, colorChooser, this, null);

        super.addActionListener((e) -> {
            colorChooser.setColor(current);
            dialog.setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setSelectedColor(colorChooser.getColor());
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
