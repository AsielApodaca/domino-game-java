
package logica;

import dominodto.CasillaDTO;
import dominodto.ConfiguracionJuegoDTO;
import dominodto.FichaDominoDTO;
import dominodto.UsuarioDTO;
import java.util.List;

/**
 *
 * @author asielapodaca
 */
public interface IPartidaServerLogica {
    public void procesarIniciarPartida(List<UsuarioDTO> usuariosDTO, ConfiguracionJuegoDTO configuracionPartida);
    public void procesarFichaSeleccionada(FichaDominoDTO ficha, UsuarioDTO usuario);
    public void procesarCasillaSeleccionada(CasillaDTO casilla, UsuarioDTO usuario);
}
