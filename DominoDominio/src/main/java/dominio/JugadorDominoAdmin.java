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
public class JugadorDominoAdmin extends JugadorDominoEntity {

    public JugadorDominoAdmin(String nombre, Icon icon, List<FichaDominoEntity> listaFichasJugador) {
        super(nombre, icon, listaFichasJugador);
    }

}