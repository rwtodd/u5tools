package org.rwtodd.u5tools.runes;

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
		return new JPanel();
	}

}
