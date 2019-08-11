package org.rwtodd.u5tools.runes;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import org.rwtodd.u5tools.spi.Tool;

public class RunesTool implements Tool {

	@Override
	public String getName() {
            return "Runes Image";
	}

	@Override
	public JComponent createUI(Runnable mainMenu) {
            var panel = new JPanel(new BorderLayout());
            var mmBtn = new JButton("Return to Main");
            mmBtn.addActionListener((e) -> mainMenu.run());
            panel.add(mmBtn,BorderLayout.NORTH);
            return panel;
	}

}
