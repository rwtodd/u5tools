/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rwtodd.u5tools.runes;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Richard Todd
 */
public class ConfigDialog extends JDialog {

    private final RunePanel.Configuration cfg;
    private final ColorButton fgButton;
    private final ColorButton bgButton;
    private final ColorButton shButton;
    private final JFormattedTextField shadowField;
    private final JFormattedTextField spacingXField;
    private final JFormattedTextField spacingYField;
    private final JFormattedTextField shadowXField;
    private final JFormattedTextField shadowYField;
    private final JFormattedTextField paddingXField;
    private final JFormattedTextField paddingYField;
    private final JTextComponent text;

    ConfigDialog(final RunePanel rp, final JTextComponent txt) {
        super((JFrame) null, "Runes Config", false);
        cfg = rp.getCfg();
        text = txt;

        // set up a panel of buttons...
        final var buttons = new JPanel();
        final var okBtn = new JButton("Ok");
        okBtn.addActionListener((e) -> {
            apply(rp);
            setVisible(false);
        });
        final var applyBtn = new JButton("Apply");
        applyBtn.addActionListener((e) -> apply(rp));

        final var cBtn = new JButton("Cancel");
        cBtn.addActionListener((e) -> {
            setVisible(false);
        });
        buttons.add(applyBtn);
        buttons.add(okBtn);
        buttons.add(cBtn);
        super.add(buttons, BorderLayout.SOUTH);

        // set up options..
        var optPanel = new JPanel(new GridLayout(0, 2));
        optPanel.add(new JLabel("Foreground:"));
        fgButton = new ColorButton(cfg.fgColor);
        optPanel.add(fgButton);

        optPanel.add(new JLabel("Background:"));
        bgButton = new ColorButton(cfg.bgColor);
        optPanel.add(bgButton);

        optPanel.add(new JLabel("Shadow:"));
        shButton = new ColorButton(cfg.shadowColor);
        optPanel.add(shButton);

        final var intFmt = NumberFormat.getIntegerInstance();
        optPanel.add(new JLabel("Shadow Blur:"));
        shadowField = new JFormattedTextField(intFmt);
        shadowField.setValue(cfg.shadowDepth);
        optPanel.add(shadowField);

        final var decimalFmt = NumberFormat.getNumberInstance();
        decimalFmt.setMaximumFractionDigits(4);
        optPanel.add(new JLabel("Shadow X:"));
        shadowXField = new JFormattedTextField(decimalFmt);
        shadowXField.setValue(cfg.shadowOffsetX);
        optPanel.add(shadowXField);

        optPanel.add(new JLabel("Shadow Y:"));
        shadowYField = new JFormattedTextField(decimalFmt);
        shadowYField.setValue(cfg.shadowOffsetY);
        optPanel.add(shadowYField);

        optPanel.add(new JLabel("Spacing X:"));
        spacingXField = new JFormattedTextField(decimalFmt);
        spacingXField.setValue(cfg.spacingX);
        optPanel.add(spacingXField);
        optPanel.add(new JLabel("Spacing Y:"));
        spacingYField = new JFormattedTextField(decimalFmt);
        spacingYField.setValue(cfg.spacingY);
        optPanel.add(spacingYField);

        optPanel.add(new JLabel("Padding X:"));
        paddingXField = new JFormattedTextField(intFmt);
        paddingXField.setValue(cfg.paddingX);
        optPanel.add(paddingXField);
        optPanel.add(new JLabel("Padding Y:"));
        paddingYField = new JFormattedTextField(intFmt);
        paddingYField.setValue(cfg.paddingY);
        optPanel.add(paddingYField);

        super.add(optPanel, BorderLayout.CENTER);
    }

    private void apply(RunePanel rp) {
        try {
            cfg.fgColor = fgButton.getSelectedColor();
            cfg.bgColor = bgButton.getSelectedColor();
            cfg.shadowColor = shButton.getSelectedColor();
            cfg.shadowDepth = ((Number)shadowField.getValue()).intValue(); // Integer.parseInt(shadowField.getText());
            cfg.paddingX = ((Number)paddingXField.getValue()).intValue();  //Integer.parseInt(paddingXField.getText());
            cfg.paddingY = ((Number)paddingYField.getValue()).intValue(); // Integer.parseInt(paddingYField.getText());
            cfg.shadowOffsetX = ((Number)shadowXField.getValue()).doubleValue(); //Double.parseDouble(shadowXField.getText());
            cfg.shadowOffsetY = ((Number)shadowYField.getValue()).doubleValue(); //Double.parseDouble(shadowYField.getText());
            var newSpX = ((Number)spacingXField.getValue()).floatValue();// Float.parseFloat(spacingXField.getText());
            var newSpY = ((Number)spacingYField.getValue()).floatValue(); //Float.parseFloat(spacingYField.getText());
            var reRender = (cfg.spacingX != newSpX) || (cfg.spacingY != newSpY);
            cfg.spacingX = newSpX;
            cfg.spacingY = newSpY;
            rp.setCfg(cfg);
            if (reRender) {
                rp.drawText(text.getText());
            }
            rp.repaint();
        } catch (Exception e) {
            /* do nothing with user error for now */
        }
    }
}
