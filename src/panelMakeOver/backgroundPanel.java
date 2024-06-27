/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package panelMakeOver;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author sad
 */
public class backgroundPanel extends JPanel{
    private Image gam;

    public backgroundPanel() {
        setOpaque(true);
        gam=new ImageIcon(getClass().getResource("/panelMakeOver/Background-Picture-85.jpg")).getImage();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gd=(Graphics2D)g.create();
        gd.drawImage(gam, 0,0,getWidth(),getHeight(),null);
        gd.dispose();
    }

}
