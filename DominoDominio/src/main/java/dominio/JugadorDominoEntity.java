/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.util.ArrayList;
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

    public JugadorDominoEntity(String idCliente, String nombre, Icon icon) {
        super(idCliente, nombre, icon);
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
        listaFichasJugador.add(ficha);
    }
}
