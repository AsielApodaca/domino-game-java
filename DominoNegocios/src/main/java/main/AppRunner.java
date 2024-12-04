
package main;

import setup.ISetup;
import setup.Setup;

/**
 *
 * @author asielapodaca
 */
public class AppRunner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ISetup setup = new Setup();
        setup.iniciar();
    }
    
}
