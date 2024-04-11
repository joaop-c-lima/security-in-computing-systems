package sics.view.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import sics.view.mf.RightMainPanel;
import sics.view.mf.rmp.CaesarCipherPanel;

/**
 *
 * @author João Lima
 */
public class CaesarCipherButtonMouseListener implements MouseListener {

    private RightMainPanel rmp;

    public CaesarCipherButtonMouseListener(RightMainPanel rmp) {
        this.rmp = rmp;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        rmp.change(new CaesarCipherPanel());
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
