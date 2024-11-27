
package logica;

import dominodto.CasillaDTO;
import dominodto.ConfiguracionJuegoDTO;
import dominodto.FichaDominoDTO;
import dominodto.UsuarioDTO;

/**
 *
 * @author asielapodaca
 */
public interface IPartidaServerLogica {
    public void procesarCrearSala(ConfiguracionJuegoDTO configuracionJuegoDTO, UsuarioDTO anfitrion);
    public void procesarUnirseSala(UsuarioDTO usuarioDTO);
    public void procesarAbandonarSala(String idCliente);
    public void procesarIniciarPartida();
    public void procesarFichaSeleccionada(FichaDominoDTO ficha, UsuarioDTO usuario);
    public void procesarCasillaSeleccionada(CasillaDTO casilla, UsuarioDTO usuario);
}
