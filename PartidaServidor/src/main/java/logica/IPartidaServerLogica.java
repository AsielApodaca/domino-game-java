
package logica;

import dominodto.CasillaDTO;
import dominodto.ConfiguracionJuegoDTO;
import dominodto.FichaDominoDTO;
import dominodto.SalaDTO;
import dominodto.UsuarioDTO;
import generadorrespuestas.IGeneradorRespuestas;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public interface IPartidaServerLogica {
    public void procesarCrearSala(ConfiguracionJuegoDTO configuracionJuegoDTO, UsuarioDTO anfitrion);
    public void procesarUnirseSala(UsuarioDTO usuarioDTO);
    public void procesarAbandonarSala(String idCliente);
    public void procesarIniciarPartida();
    public void procesarFichaSeleccionada(FichaDominoDTO ficha, UsuarioDTO usuario);
    public void procesarCasillaSeleccionada(CasillaDTO casilla, UsuarioDTO usuario);
    public void procesarSacarFichaPozo(String idCliente);
    public void setGeneradorRespuestas(IGeneradorRespuestas generadorRespuestas);
    public void procesarMostrarSalaDisponible();
}
