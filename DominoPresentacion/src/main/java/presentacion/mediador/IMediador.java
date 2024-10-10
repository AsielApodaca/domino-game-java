/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package presentacion.mediador;

import contenedorpantallas.FormContenedorController;
import contenedorpantallas.IContenidoController;
import java.util.List;
import presentacion.partidadomino.fichadomino.FichaDominoModel;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public interface IMediador {
    void crearFichasJugadorLocal();
    List<FichaDominoModel> obtenerFichasJugadorLocal();
    void redimencionarFichasJugadorLocal();
    public void setContenedorController(FormContenedorController contenedorController);
    public void setContenidoController(IContenidoController contenidoController);
    public void notificarEscalaAContenido();
    public void mostrarPantalla();

}

