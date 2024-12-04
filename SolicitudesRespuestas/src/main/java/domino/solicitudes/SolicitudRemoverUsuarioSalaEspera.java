package domino.solicitudes;

import dominodto.UsuarioDTO;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class SolicitudRemoverUsuarioSalaEspera extends EventoSolicitud{
    
    public SolicitudRemoverUsuarioSalaEspera(UsuarioDTO usuarioDTO) {
        super(usuarioDTO);
    }
    
    
}
