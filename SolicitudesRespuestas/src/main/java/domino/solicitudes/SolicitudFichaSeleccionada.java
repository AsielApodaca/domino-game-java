package domino.solicitudes;

import dominodto.FichaDominoDTO;
import dominodto.UsuarioDTO;

/**
 * Clase que representa una solicitud para seleccionar una ficha de dominó.
 * Extiende la clase {@link EventoSolicitud}.
 * 
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class SolicitudFichaSeleccionada extends EventoSolicitud {

    private FichaDominoDTO fichaDominoDTO;

    /**
     * Constructor que inicializa la solicitud con una ficha de dominó y un usuario.
     * 
     * @param fichaDominoDTO la ficha de dominó seleccionada.
     * @param usuarioDTO el usuario asociado a la solicitud.
     */
    public SolicitudFichaSeleccionada(FichaDominoDTO fichaDominoDTO, UsuarioDTO usuarioDTO) {
        super(usuarioDTO);
        this.fichaDominoDTO = fichaDominoDTO;
    }

    /**
     * Obtiene la ficha de dominó asociada a la solicitud.
     * 
     * @return el objeto {@link FichaDominoDTO}.
     */
    public FichaDominoDTO getFichaDominoDTO() {
        return fichaDominoDTO;
    }
}
