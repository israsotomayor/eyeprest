package vistas;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Isra
 */
public class Main {
    
    public static void main(String[] args) {
        // TODO code application logic here
        
         try {
            //            JFrame.setDefaultLookAndFeelDecorated(true);
            //            SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.SaharaSkin");
            ////Blue Ice
            //Blue Moon
            //WhiteVision
            //Simple2D
            //
            //UIManager.setLookAndFeel(new SyntheticaSimple2DLookAndFeel());
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());            
            new Principal().setVisible(true);
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}