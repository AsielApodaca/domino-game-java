/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package presentacion.mediador;

import dominodto.FichaDominoDTO;
import presentacion.partidadomino.PartidaDominoController;
import presentacion.partidadomino.fichadominojugador.FichaDominoController;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public interface IMediador {
    void crearFichasJugadorLocalView();
    void redimencionarFichasJugadorLocal(float escala);
    void notificarFichaSeleccionada(FichaDominoDTO fichaSeleccionada) ;
    void notificarColocarFicha() ;
    void deseleccionarRestoDeFichas(FichaDominoController fichaSeleccionada);
    void notificarFichaDeseleccionada();
    void setPartidaDominoController(PartidaDominoController partidaDominoController);

}

