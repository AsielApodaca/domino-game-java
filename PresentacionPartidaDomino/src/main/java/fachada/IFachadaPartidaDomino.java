package fachada;

import dominodto.CasillaDTO;
import dominodto.FichaDominoDTO;
import java.util.List;
import listeners.IContenedorListener;
import listeners.IPresentacionPartidaDominoListener;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public interface IFachadaPartidaDomino {

    public IContenedorListener iniciarPantalla();

    public void mostrarFichasJugadorLocal(List<FichaDominoDTO> fichasJugador);

    public void colocarFichaTablero(CasillaDTO casillaDTO);

    public void mostrarCasillasParaColocarFicha(List<CasillaDTO> casillasDTO);
    
    public void ocultarCasillasParaColocarFicha();
    
    public void suscribirPresentacionListener(IPresentacionPartidaDominoListener listener);
}
