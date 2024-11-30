package listeners;

import dominodto.CasillaDTO;
import dominodto.FichaDominoDTO;

/**
 *
 * @author asielapodaca
 *
 * Interfaz de listener que estará escuchando los eventos de este componente
 * PresentacionPartidaDomino.
 */
public interface IPresentacionPartidaDominoListener {
    public void onFichaSeleccionada(FichaDominoDTO fichaSeleccionada);
    public void onCasillaSeleccionada(CasillaDTO casillaSeleccionada);
}
