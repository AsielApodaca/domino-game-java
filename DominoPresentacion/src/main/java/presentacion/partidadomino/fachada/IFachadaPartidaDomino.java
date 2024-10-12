/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package presentacion.partidadomino.fachada;

import dominodto.CasillaDTO;
import dominodto.FichaDominoDTO;
import java.util.List;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public interface IFachadaPartidaDomino {
    void iniciarPantalla();
    void mostrarFichas(List<FichaDominoDTO> fichasJugador);
    public void actualizarFichasJugadorLocal(List<FichaDominoDTO> listaFichasDomino);
    public void colocarFichaTablero(CasillaDTO casillaDTO);
    public void mostrarCasillaParaColocarFicha(CasillaDTO casillaDTO);
}