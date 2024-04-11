package sics.view.styles;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author João Lima
 */
public class RoundedTextArea extends RoundedPanel {

    private JTextArea textArea;
    private JScrollPane scroll;

    public RoundedTextArea(int cornerRadius) {
        super(cornerRadius);
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setLayout(new GridBagLayout());
        setOpaque(false);

        textArea = new JTextArea();
        textArea.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        textArea.setOpaque(false);
        
        scroll = new JScrollPane(textArea);
        scroll.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(scroll, gbc);

    }

    public JTextArea getTextArea() {
        return textArea;
    }

}
