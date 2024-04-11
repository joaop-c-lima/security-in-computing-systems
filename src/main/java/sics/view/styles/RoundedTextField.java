package sics.view.styles;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JTextField;

/**
 *
 * @author João Lima
 */
public class RoundedTextField extends RoundedPanel {

    private JTextField textField;

    public RoundedTextField(int cornerRadius) {
        super(cornerRadius);
        setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        setLayout(new GridBagLayout());
        setOpaque(false);

        textField = new JTextField();
        textField.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        textField.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(textField, gbc);
        
    }

    public JTextField getTextField() {
        return textField;
    }
    
    

}
