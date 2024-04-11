package sics.view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import sics.view.mf.LeftMainPanel;
import sics.view.mf.RightMainPanel;
import sics.view.mf.rmp.HomePanel;

/**
 *
 * @author João Lima
 */
public class MainFrame extends JFrame {

    private JSplitPane sp;
    private LeftMainPanel lmp;
    private RightMainPanel rmp;

    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Security in Computing Systems");
        setResizable(true);
        setMinimumSize(new Dimension(1024, 768));
        setBackground(Color.decode("#d3d1ce"));

        rmp = new RightMainPanel(this);
        rmp.change(new HomePanel());
        lmp = new LeftMainPanel(this);

        sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, lmp, rmp);
        sp.setOpaque(false);

        add(sp);
    }

    public LeftMainPanel getLmp() {
        return lmp;
    }

    public RightMainPanel getRmp() {
        return rmp;
    }

}
