package sics.view.mf.rmp;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import sics.view.styles.RoundedPanel;

/**
 *
 * @author João Lima
 */
public class HomePanel extends RoundedPanel {

    private JLabel title;

    public HomePanel() {
        super(10);
        setBackground(Color.decode("#ffffff"));
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        title = new JLabel("Security in Computing Systems");
        title.setFont(new Font("Helvetica", Font.PLAIN, 30));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.weightx = 1;
        gbc.weighty = 1;
        add(title, gbc);
    }

}
