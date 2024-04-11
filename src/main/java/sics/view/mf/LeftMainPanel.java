package sics.view.mf;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import sics.view.MainFrame;
import sics.view.mf.lmp.MenuPanel;

/**
 *
 * @author João Lima
 */
public class LeftMainPanel extends JPanel {

    private MainFrame mainFrame;
    private JLabel logo;
    private JPanel menuPanel;
    private ImageIcon image;

    public LeftMainPanel(MainFrame mainFrame) {
        setBackground(Color.decode("#d3d1ce"));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setLayout(new GridBagLayout());

        this.mainFrame = mainFrame;

        image = new ImageIcon(LeftMainPanel.class.getResource("/sicsLogo.png"));

        logo = new JLabel(image);
        logo.setPreferredSize(new Dimension(200, 150));

        menuPanel = new MenuPanel(this);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(logo, gbc);
        gbc.gridy += 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(menuPanel, gbc);

    }

    public JPanel getMenuPanel() {
        return menuPanel;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

}
