package mapeodto;

import dominio.ConfiguracionJuegoEntity;
import dominio.UsuarioEntity;
import dominodto.ConfiguracionJuegoDTO;
import dominodto.UsuarioDTO;

/**
 * Clase encargada de mapear las entidades del dominio a sus respectivos 
 * Data Transfer Objects (DTO). Este mapeador facilita la transferencia de datos
 * entre capas, asegurando que las estructuras de dominio no se expongan
 * directamente al resto del sistema.
 * 
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class MapeadorDTO {

    /**
     * Mapea una entidad de usuario a su correspondiente DTO.
     *
     * @param usuarioEntity La entidad de usuario a mapear.
     * @return Un objeto {@link UsuarioDTO} que representa al usuario.
     */
    public static UsuarioDTO UsuarioEntityADTO(UsuarioEntity usuarioEntity) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        
        // Mapea los atributos de la entidad UsuarioEntity a UsuarioDTO
        usuarioDTO.setIcon(usuarioEntity.getIcon());
        usuarioDTO.setIdCliente(usuarioEntity.getIdCliente());
        usuarioDTO.setNombre(usuarioEntity.getNombre());
        
        return usuarioDTO;
    }
    
    /**
     * Mapea una entidad de configuración de juego a su correspondiente DTO.
     *
     * @param configuracionJuegoEntity La entidad de configuración de juego a mapear.
     * @return Un objeto {@link ConfiguracionJuegoDTO} que representa la configuración del juego.
     */
    public static ConfiguracionJuegoDTO configuracionJuegoEntityADTO(ConfiguracionJuegoEntity configuracionJuegoEntity) {
        ConfiguracionJuegoDTO configuracionJuegoDTO = new ConfiguracionJuegoDTO();
        
        // Mapea los atributos de la entidad ConfiguracionJuegoEntity a ConfiguracionJuegoDTO
        configuracionJuegoDTO.setFichasPorJugador(configuracionJuegoEntity.getFichasPorJugador());
        configuracionJuegoDTO.setLimiteJugadores(configuracionJuegoEntity.getLimiteJugadores());
        configuracionJuegoDTO.setNombreSala(configuracionJuegoEntity.getNombreSala());
        
        return configuracionJuegoDTO;
    }
}
