package org.rwtodd.u5tools.spi;

import javax.swing.JComponent;

/**
 * Interface that service providers will use to provide custom tools.
 * @author Richard Todd
 *
 */
public interface Tool {
	/**
	 * provides the name by which the tool wants to be referred in the main menu.
	 * @return the name of the tool.
	 */
	String getName();
	
	/**
	 * creates a user interface for the tool.  It should allow the user to go back to the
	 * main menu via the provided function, {@code mainMenu}.
	 * 
	 * @param mainMenu a Runnable that the UI should use to go back to the main menu.
	 * @return the tool's UI
	 */
	JComponent createUI(Runnable mainMenu);
}
