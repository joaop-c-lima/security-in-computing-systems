package sics.view.styles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author João Lima
 */
public class MenuButton extends RoundedPanel {

    private static JLabel iconLabel;
    private static JLabel textLabel;
    private static JPanel prot;
    private static JPanel voidPanel;

    public MenuButton(String text, Icon icon) {
        super(10);
        setPreferredSize(new Dimension(1, 35));
        setBackground(Color.decode("#bababa"));
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        addMouseListener(new MenuButtonMouseListener(this));

        iconLabel = new JLabel(icon);
        iconLabel.setPreferredSize(new Dimension(20, 20));

        textLabel = new JLabel(text);
        textLabel.setFont(new Font("Helvetica", Font.PLAIN, 15));

        voidPanel = new JPanel();
        voidPanel.setPreferredSize(new Dimension(1, 1));
        voidPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 0, 0, 5);
        add(iconLabel, gbc);
        gbc.gridx++;
        add(textLabel, gbc);
        gbc.gridx++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        add(voidPanel, gbc);

    }

    private class MenuButtonMouseListener implements MouseListener {

        private MenuButton menuButton;
        private boolean isOutside;

        public MenuButtonMouseListener(MenuButton menuButton) {
            this.menuButton = menuButton;
            this.isOutside = true;
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            isOutside = false;
            menuButton.setBackground(Color.decode("#e9e9e9"));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (!isOutside) {
                menuButton.setBackground(Color.decode("#bababa"));
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            isOutside = false;
            menuButton.setBackground(Color.decode("#dedede"));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            isOutside = true;
            menuButton.setBackground(Color.decode("#bababa"));
        }

    }

}
