package org.rwtodd.u5tools.runes;

import java.awt.BorderLayout;
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
        var panel = new JPanel(new BorderLayout());
        var controls = new JPanel(new BorderLayout());
        var mmBtn = new JButton("Return to Main");
        mmBtn.addActionListener((e) -> mainMenu.run());
        controls.add(mmBtn, BorderLayout.NORTH);
        final var text = new JTextArea(20,10);
        controls.add(text, BorderLayout.CENTER);
        panel.add(controls, BorderLayout.WEST);
        
        final var rp = new RunePanel();
        panel.add(rp, BorderLayout.CENTER);
        
        text.getDocument().addDocumentListener(new DocumentListener() {
            private void onChange() {
                rp.drawText(text.getText());
            }
            
            @Override
            public void insertUpdate(DocumentEvent e) { onChange(); }

            @Override
            public void removeUpdate(DocumentEvent e) { onChange(); }

            @Override
            public void changedUpdate(DocumentEvent e) { onChange(); }
        
        });
        return panel;
    }

}
