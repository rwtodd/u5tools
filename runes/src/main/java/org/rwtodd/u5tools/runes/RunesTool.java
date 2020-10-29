package org.rwtodd.u5tools.runes;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.rwtodd.u5tools.spi.Tool;

public class RunesTool implements Tool {

    @Override
    public String getName() {
        return "Runes Image";
    }

    @Override
    public JComponent createUI(Runnable mainMenu) {
        final var rp = new RunePanel();
        final var text = new JTextArea(20, 10);
        text.setText("do you\nspeak avatar");
        rp.drawText(text.getText());
        
        var panel = new JPanel(new BorderLayout());
        var sideBar = new JPanel(new BorderLayout());
        var controls = new JPanel(new GridLayout(0,1));

        var optBtn = new JButton("Options");
        optBtn.addActionListener((e) -> {
            var dlg = new ConfigDialog(rp,text);
            dlg.pack();
            dlg.setVisible(true);
        });
        controls.add(optBtn);
        var mmBtn = new JButton("Return to Main");
        mmBtn.addActionListener((e) -> mainMenu.run());
        controls.add(mmBtn);
        sideBar.add(controls,BorderLayout.SOUTH);
  
        sideBar.add(text, BorderLayout.CENTER);
        panel.add(sideBar, BorderLayout.WEST);

        panel.add(rp, BorderLayout.CENTER);

        text.getDocument().addDocumentListener(new DocumentListener() {
            private void onChange() {
                rp.drawText(text.getText());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                onChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                onChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                onChange();
            }

        });
        return panel;
    }

}
