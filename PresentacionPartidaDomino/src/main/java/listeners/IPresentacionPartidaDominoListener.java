package listeners;

import dominodto.CasillaDTO;
import dominodto.FichaDominoDTO;

/**
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 *
 * Interfaz de listener que estará escuchando los eventos de este componente
 * PresentacionPartidaDomino.
 */
public interface IPresentacionPartidaDominoListener {
    public void onFichaSeleccionada(FichaDominoDTO fichaSeleccionada);
    public void onCasillaSeleccionada(CasillaDTO casillaSeleccionada);
    public void onPozoSeleccionado();
    
}
