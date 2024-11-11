package notificador.eventos;

import dominodto.CasillaDTO;

/**
 *
 * @author asielapodaca
 * 
 * Evento de cuando el jugador local hace click en una casilla disponible en el
 * tablero para colocar una ficha compatible del jugador
 * 
 * CasillaDTO casillaDTO almacena cual extremo de la cadena de fichas fué la
 * seleccionada.
 */
public class CasillaSeleccionadaEvento {
    private CasillaDTO casillaDTO;

    public CasillaSeleccionadaEvento(CasillaDTO casillaDTO) {
        this.casillaDTO = casillaDTO;
    }

    public CasillaDTO getCasillaDTO() {
        return casillaDTO;
    }
}
