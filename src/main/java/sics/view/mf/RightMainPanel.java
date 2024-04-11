package sics.view.mf;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import sics.view.MainFrame;

/**
 *
 * @author João Lima
 */
public class RightMainPanel extends JPanel {

    private static MainFrame mainFrame;
    private GridBagConstraints gbc;

    public RightMainPanel(MainFrame mainFrame) {
        setBackground(Color.decode("#d3d1ce"));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setLayout(new GridBagLayout());
        
        this.mainFrame = mainFrame;

        gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
    }

    public void change(JPanel panel) {
        removeAll();
        revalidate();
        repaint();
        add(panel, gbc);
    }

}
