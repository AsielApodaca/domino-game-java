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
 * @author asielapodaca
 */
public interface IFachadaPartidaDomino {
    public void iniciarPantalla();
    public void actualizarFichasJugadorLocal(List<FichaDominoDTO> listaFichasDomino);
    public void colocarFichaTablero(CasillaDTO casillaDTO);
    public void mostrarCasillaParaColocarFicha(CasillaDTO casillaDTO);
}
