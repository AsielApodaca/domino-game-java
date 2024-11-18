package domino.solicitudes;

import dominodto.UsuarioDTO;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Clase abstracta que representa un evento de solicitud dentro del sistema de dominó.
 * Los eventos de solicitud contienen información relacionada con el cliente 
 * y el usuario asociado a la solicitud.
 * 
 * Proporciona métodos para establecer y obtener el ID del cliente y el objeto
 * {@link UsuarioDTO} correspondiente.
 * 
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public abstract class EventoSolicitud {

    private static final Logger LOG = Logger.getLogger(EventoSolicitud.class.getName());
    private String idCliente;
    private UsuarioDTO usuarioDTO;

    /**
     * Constructor que inicializa el evento con un objeto {@link UsuarioDTO}.
     * 
     * @param usuarioDTO el objeto UsuarioDTO asociado al evento.
     */
    public EventoSolicitud(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
    }

    /**
     * Establece el ID del cliente asociado al evento.
     * Si ya existe un {@link UsuarioDTO}, también actualiza su ID.
     * 
     * @param idCliente el ID del cliente que se desea asociar.
     */
    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
        if (usuarioDTO != null) {
            this.usuarioDTO.setIdCliente(idCliente);
        } else {
            LOG.log(Level.WARNING, "idCliente asignado a evento, pero sin usuarioDTO existente.");
        }
    }

    /**
     * Obtiene el ID del cliente asociado al evento.
     * 
     * @return el ID del cliente.
     */
    public String getIdCliente() {
        return idCliente;
    }

    /**
     * Obtiene el objeto {@link UsuarioDTO} asociado al evento.
     * 
     * @return el objeto UsuarioDTO, o {@code null} si no se ha asignado.
     */
    public UsuarioDTO getUsuarioDTO() {
        return usuarioDTO;
    }

    /**
     * Establece el objeto {@link UsuarioDTO} asociado al evento.
     * 
     * @param usuarioDTO el objeto UsuarioDTO que se desea asociar.
     */
    public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
    }
}
