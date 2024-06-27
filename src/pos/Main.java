/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pos;

import javax.swing.UIManager;

/**
 *
 * @author User
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try
        {
          com.jtattoo.plaf.mcwin.McWinLookAndFeel.setTheme("Large-Font", "Java Swing", "");
          UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
           new mod_logsys(null, true).setVisible(true);
        } catch(Exception a){
            a.printStackTrace();
       }
    }

}
