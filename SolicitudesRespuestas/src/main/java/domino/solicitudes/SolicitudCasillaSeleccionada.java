package domino.solicitudes;

import dominodto.CasillaDTO;
import dominodto.UsuarioDTO;

/**
 * Clase que representa una solicitud para seleccionar una casilla específica
 * en el tablero durante el juego de dominó. Hereda de {@link EventoSolicitud}.
 * 
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class SolicitudCasillaSeleccionada extends EventoSolicitud {

    private CasillaDTO casillaDTO;

    /**
     * Constructor que inicializa la solicitud con la casilla seleccionada y el usuario asociado.
     * 
     * @param casillaDTO la casilla seleccionada.
     * @param usuarioDTO el usuario que realizó la solicitud.
     */
    public SolicitudCasillaSeleccionada(CasillaDTO casillaDTO, UsuarioDTO usuarioDTO) {
        super(usuarioDTO);
        this.casillaDTO = casillaDTO;
    }

    /**
     * Devuelve la casilla asociada a esta solicitud.
     * 
     * @return el objeto {@link CasillaDTO} que representa la casilla seleccionada.
     */
    public CasillaDTO getCasillaDTO() {
        return casillaDTO;
    }
}
