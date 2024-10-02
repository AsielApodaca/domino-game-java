/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dev.itson.dominopresentacion.tablerodomino;

import dev.itson.dominodominio.FichaDomino;
import java.util.List;
import javax.swing.JButton;

/**
 *
 * @author olive
 */
public interface ITableroDominoModeloListener {
    
    void onChangeListaFichasUsuario(List<FichaDomino> listaFichasUsuario) ;
    
    void onChangeListaBotonesFichasUsuario(List<JButton> listaBotonesFichasUsuario) ;
    
    void onChangeFichaComparativa(JButton fichaComparativa) ;
    
}
