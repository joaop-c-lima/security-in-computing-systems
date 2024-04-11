package sics.view.mf.lmp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import sics.view.listeners.CaesarCipherButtonMouseListener;
import sics.view.listeners.HomeButtonMouseListener;
import sics.view.mf.LeftMainPanel;
import sics.view.styles.MenuButton;
import sics.view.styles.RoundedPanel;

/**
 *
 * @author João Lima
 */
public class MenuPanel extends RoundedPanel {

    private LeftMainPanel lmp;
    private MenuButton homeButton;
    private MenuButton caesarCipherButton;
    private JPanel voidPanel;

    public MenuPanel(LeftMainPanel lmp) {
        super(10);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setLayout(new GridBagLayout());

        this.lmp = lmp;
        
        homeButton = new MenuButton("Home", new ImageIcon(LeftMainPanel.class.getResource("/homeLogo.png")));
        homeButton.addMouseListener(new HomeButtonMouseListener(lmp.getMainFrame().getRmp()));

        caesarCipherButton = new MenuButton("Caesar Cipher", new ImageIcon(LeftMainPanel.class.getResource("/caesarLogo.png")));
        caesarCipherButton.addMouseListener(new CaesarCipherButtonMouseListener(lmp.getMainFrame().getRmp()));

        voidPanel = new JPanel();
        voidPanel.setPreferredSize(new Dimension(1, 1));
        voidPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(homeButton, gbc);
        gbc.gridy += 1;
        add(caesarCipherButton, gbc);
        gbc.gridy += 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(voidPanel, gbc);

    }

    public MenuButton getHomeButton() {
        return homeButton;
    }

    public MenuButton getCaesarCipherButton() {
        return caesarCipherButton;
    }

}
