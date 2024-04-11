package sics.view.mf.rmp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import sics.algorithms.CaesarCipher;
import sics.view.styles.RoundedPanel;
import sics.view.styles.RoundedTextArea;
import sics.view.styles.RoundedTextField;

/**
 *
 * @author João Lima
 */
public class CaesarCipherPanel extends RoundedPanel {

    private JLabel title;
    private JLabel keyLabel;
    private RoundedTextField keyField;
    private JLabel plainTxtLabel;
    private RoundedTextArea plainTxtArea;
    private JLabel cipherTxtLabel;
    private RoundedTextArea cipherTxtArea;
    private JLabel cryptAnalysisLabel;
    private RoundedTextArea cryptAnalysisArea;

    private CaesarCipher caesarCipher;

    public CaesarCipherPanel() {
        super(10);
        setBackground(Color.decode("#ffffff"));
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        caesarCipher = new CaesarCipher();

        title = new JLabel("Caesar Cipher");
        title.setFont(new Font("Helvetica", Font.PLAIN, 30));

        keyLabel = new JLabel("Key");
        keyLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));

        keyField = new RoundedTextField(10);
        keyField.setPreferredSize(new Dimension(90, 30));
        keyField.setBackground(Color.decode("#d3d1ce"));
        keyField.getTextField().setText("0");
        keyField.getTextField().addKeyListener(new CaesarCipherKeyAdapter(this));

        plainTxtLabel = new JLabel("Plain Text");
        plainTxtLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));

        plainTxtArea = new RoundedTextArea(10);
        plainTxtArea.setPreferredSize(new Dimension(30, 30));
        plainTxtArea.setBackground(Color.decode("#d3d1ce"));
        plainTxtArea.getTextArea().addKeyListener(new CaesarCipherKeyAdapter(this));
        
        cipherTxtLabel = new JLabel("Cipher Text");
        cipherTxtLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));

        cipherTxtArea = new RoundedTextArea(10);
        cipherTxtArea.setPreferredSize(new Dimension(30, 30));
        cipherTxtArea.setBackground(Color.decode("#d3d1ce"));
        cipherTxtArea.getTextArea().setEditable(false);

        cryptAnalysisLabel = new JLabel("Crypt Analysis");
        cryptAnalysisLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));

        cryptAnalysisArea = new RoundedTextArea(10);
        cryptAnalysisArea.setPreferredSize(new Dimension(30, 30));
        cryptAnalysisArea.setBackground(Color.decode("#d3d1ce"));
        cryptAnalysisArea.getTextArea().setEditable(false);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(title, gbc);
        gbc.gridy += 1;
        add(keyLabel, gbc);
        gbc.gridy += 1;
        add(keyField, gbc);
        gbc.gridy += 1;
        gbc.fill = GridBagConstraints.NONE;
        add(plainTxtLabel, gbc);
        gbc.gridy += 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(plainTxtArea, gbc);
        gbc.gridy += 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        add(cipherTxtLabel, gbc);
        gbc.gridy += 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(cipherTxtArea, gbc);
        gbc.gridy += 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        add(cryptAnalysisLabel, gbc);
        gbc.gridy += 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(cryptAnalysisArea, gbc);

    }

    public void encrypt() {
        if (!keyField.getTextField().getText().isEmpty() && !plainTxtArea.getTextArea().getText().isEmpty()) {
            try {
                int key = Integer.parseInt(keyField.getTextField().getText());
                String plainText = plainTxtArea.getTextArea().getText();
                boolean changed = false;
                if (caesarCipher.getKey() != key) {
                    caesarCipher.setKey(key);
                    changed = true;
                } else if (caesarCipher.getPlainText() != plainText) {
                    caesarCipher.setPlainText(plainText);
                    changed = true;
                }
                if (changed) {
                    cipherTxtArea.getTextArea().setText(caesarCipher.getCipherText());
                    cryptAnalysisArea.getTextArea().setText(caesarCipher.getCryptAnalysis());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class CaesarCipherKeyAdapter extends KeyAdapter {

        private CaesarCipherPanel panel;

        public CaesarCipherKeyAdapter(CaesarCipherPanel panel) {
            this.panel = panel;
        }

        @Override
        public void keyReleased(KeyEvent e) {
            panel.encrypt();
        }

    }

}
