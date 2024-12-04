package logica.salaesperalogica;

import dominodto.UsuarioDTO;
import listeners.IContenedorListener;

/**
 * Interfaz que define las operaciones relacionadas con la lógica de la sala de espera
 * en un juego de dominó. Permite gestionar usuarios y realizar acciones específicas
 * en la sala de espera, como otorgar permisos de anfitrión y manejar la lista de usuarios.
 * 
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public interface ISalaEsperaLogica {
 
    /**
     * Inicia la sala de espera y establece los listeners necesarios.
     * 
     * @return Un contenedor de listeners configurado para manejar eventos en la sala.
     */
    public IContenedorListener iniciar();
    
    public void crearSala();
    
    public void unirseSala();

    /**
     * Otorga permisos de anfitrión al usuario actual. Este método está destinado
     * a designar al anfitrión responsable de la sala de espera.
     */
    public void otorgarPermisosDeAnfitrion();

    /**
     * Agrega un usuario a la sala de espera.
     * 
     * @param usuarioDTO El usuario que será agregado a la sala.
     */
    public void agregarUsuarioASala(UsuarioDTO usuarioDTO);

    /**
     * Elimina un usuario de la sala de espera.
     * 
     * @param usuarioDTO El usuario que será eliminado de la sala.
     */
    public void removerUsuarioDeSala(UsuarioDTO usuarioDTO);
}