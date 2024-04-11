package sics.view.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import sics.view.mf.RightMainPanel;
import sics.view.mf.rmp.HomePanel;

/**
 *
 * @author João Lima
 */
public class HomeButtonMouseListener implements MouseListener {

    private RightMainPanel rmp;

    public HomeButtonMouseListener(RightMainPanel rmp) {
        this.rmp = rmp;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        rmp.change(new HomePanel());
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
