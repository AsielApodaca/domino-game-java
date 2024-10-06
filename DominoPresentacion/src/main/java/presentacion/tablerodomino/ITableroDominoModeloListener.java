/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package presentacion.tablerodomino;

import dominio.FichaDominoEntity;
import java.util.List;
import javax.swing.JButton;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public interface ITableroDominoModeloListener {

    void onChangeListaFichasUsuario(List<FichaDominoEntity> listaFichasUsuario);

    void onChangeFichaComparativa(FichaDominoEntity fichaComparativa);

    void onChangeFichasComparativas(List<FichaDominoEntity> listaFichasComparativas);

    void onChangeFichaSeleccionada(FichaDominoEntity fichaSeleccionada);

}
