
package dominio;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class JugadorDominoEntity extends UsuarioEntity {

    private List<FichaDominoEntity> listaFichasJugador;

    public JugadorDominoEntity(String idCliente, String nombre, BufferedImage icono) {
        super(idCliente, nombre, icono);
        this.listaFichasJugador  = new ArrayList<>();
    }

    public JugadorDominoEntity(String idCliente, String nombre) {
        super(idCliente, nombre);
    }

    public List<FichaDominoEntity> getListaFichasJugador() {
        return listaFichasJugador;
    }

    public void setListaFichasJugador(List<FichaDominoEntity> listaFichasJugador) {
        this.listaFichasJugador = listaFichasJugador;
    }

    public void agregarFichas(List<FichaDominoEntity> fichas) {
        listaFichasJugador.addAll(fichas);
    }
    
    public void quitarFicha(FichaDominoEntity ficha) {
        listaFichasJugador.remove(ficha);
    }
}
