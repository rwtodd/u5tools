package org.rwtodd.u5tools;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ServiceLoader;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import org.rwtodd.u5tools.spi.Tool;

/**
 * This class brings up a menu of the available tools.
 *
 * @author Richard Todd
 *
 */
public class Cmd extends JFrame {

    private static final long serialVersionUID = 5070382008616192105L;

    final JPanel mainMenu;

    Cmd() {
        super("Ultima V Tools");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainMenu = lookupAvailableTools();
        displayToolList();
    }

    /**
     * Uses a service loader to find all the tools. Then, it builds a JPanel
     * with a menu of the options.
     *
     * @return a panel listing all the options
     */
    private JPanel lookupAvailableTools() {
        var p = new JPanel(new BorderLayout());
        var sl = ServiceLoader.load(Tool.class);
        var h1 = new JLabel("<html><h1>Main Menu</h1></html>");
        h1.setHorizontalAlignment(SwingConstants.CENTER);
        p.add(h1, BorderLayout.NORTH);
        var menu = new JPanel(new GridLayout(0,1,0,5));
        for (var tool : sl) {
            final var btn = new JButton(tool.getName());
            btn.addActionListener((e) -> switchPanels(tool));
            menu.add(btn);
        }
        p.add(new JScrollPane(menu), BorderLayout.CENTER);

        return p;
    }

    /**
     * Make the tool list the current display.
     */
    private void displayToolList() {
        setContentPane(mainMenu);
        pack();
        setVisible(true);
    }

    /**
     * Change the display to one of the tools.
     */
    private void switchPanels(Tool tgt) {
        setContentPane(tgt.createUI(this::displayToolList));
        pack();
        repaint();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Cmd::new);
    }

}
