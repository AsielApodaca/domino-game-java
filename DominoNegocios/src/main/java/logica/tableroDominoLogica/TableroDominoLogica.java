/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package logica.tableroDominoLogica;

import asistenteNavegacion.AsistenteNavegacionPantallas;
import presentacion.tablerodomino.TableroDominoController;
import presentacion.tablerodomino.TableroDominoModel;
import presentacion.tablerodomino.TableroDominoView;

/**
 *
 * @author Asiel Apodaca Monge
 */
public class TableroDominoLogica implements ITableroDominoLogica{
    
    
    @Override
    public void crearYMostrarPantalla() {
        TableroDominoModel model = new TableroDominoModel();
        TableroDominoView view = new TableroDominoView(model);
        TableroDominoController controller = new TableroDominoController(model, view);
        AsistenteNavegacionPantallas.getInstance().navegarA(view);
    }
    
}
