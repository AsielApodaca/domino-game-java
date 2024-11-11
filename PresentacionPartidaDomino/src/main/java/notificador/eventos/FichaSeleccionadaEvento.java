package notificador.eventos;

import dominodto.FichaDominoDTO;

/**
 *
 * @author asielapodaca
 * 
 * Evento de cuando el jugador local hace click a una ficha compatible con la
 * cadena de fichas sobre el tablero.
 * 
 * FichaDominoDTO fichaDominoDTO almacena la ficha que el jugador local
 * seleccionó para verificar los extremos compatibles en la cadena de fichas
 * sobre el tablero.
 */
public class FichaSeleccionadaEvento {
    private FichaDominoDTO fichaDominoDTO;

    public FichaSeleccionadaEvento(FichaDominoDTO fichaDominoDTO) {
        this.fichaDominoDTO = fichaDominoDTO;
    }

    public FichaDominoDTO getFichaDominoDTO() {
        return fichaDominoDTO;
    }
}
