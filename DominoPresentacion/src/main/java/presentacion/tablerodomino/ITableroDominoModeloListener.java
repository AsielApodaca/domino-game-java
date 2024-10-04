/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package presentacion.tablerodomino;

import dominio.FichaDomino;
import java.util.List;
import javax.swing.JButton;

/**
 *
 * @author olive
 */
public interface ITableroDominoModeloListener {
    
    void onChangeListaFichasUsuario(List<FichaDomino> listaFichasUsuario) ;
        
    void onChangeFichaComparativa(FichaDomino fichaComparativa) ;
    
}
