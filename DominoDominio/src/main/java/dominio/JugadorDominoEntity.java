/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.util.List;
import javax.swing.Icon;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class JugadorDominoEntity extends UsuarioEntity {

    private List<FichaDominoEntity> listaFichasJugador;

    public JugadorDominoEntity(String nombre, Icon icon, List<FichaDominoEntity> listaFichasJugador) {
        super(nombre, icon);
        this.listaFichasJugador = listaFichasJugador;
    }

    public List<FichaDominoEntity> getListaFichasJugador() {
        return listaFichasJugador;
    }

    public void setListaFichasJugador(List<FichaDominoEntity> listaFichasJugador) {
        this.listaFichasJugador = listaFichasJugador;
    }

}
