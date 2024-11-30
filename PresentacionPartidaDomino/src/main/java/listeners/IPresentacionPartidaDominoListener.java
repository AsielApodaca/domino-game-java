package listeners;

import notificador.eventos.CasillaSeleccionadaEvento;
import notificador.eventos.FichaSeleccionadaEvento;
import notificador.eventos.PozoSeleccionadoEvento;

/**
 *
 * @author asielapodaca
 *
 * Interfaz de listener que estará escuchando los eventos de este componente
 * PresentacionPartidaDomino.
 */
public interface IPresentacionPartidaDominoListener {

    public void onFichaSeleccionada(FichaSeleccionadaEvento evento);

    public void onCasillaSeleccionada(CasillaSeleccionadaEvento evento);

    public void onPozoSeleccionado(PozoSeleccionadoEvento evento);
}
