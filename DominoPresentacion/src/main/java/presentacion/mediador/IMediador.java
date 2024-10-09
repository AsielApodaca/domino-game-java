/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package presentacion.mediador;

import dominodto.FichaDominoDTO;
import java.util.List;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public interface IMediador {
    void actualizarListaFichasJugadorLocal(List<FichaDominoDTO> fichas);
    List<FichaDominoDTO> obtenerListaFichasJugadorLocal();
    float obtenerEscala();
    void actualizarEscala(float escala);
    int getRedimencionFichasJugadorLocalAncho();
    int getRedimencionFichasJugadorLocalAlto();

    void crearNuevaFicha(FichaDominoDTO fichaDominoDTO);


}

