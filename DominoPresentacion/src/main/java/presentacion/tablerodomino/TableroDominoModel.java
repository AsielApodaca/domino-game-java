/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.tablerodomino;

import dev.itson.dominodominio.FichaDomino;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JButton;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 * @author Gael Rafael Castro Molina
 */
public class TableroDominoModel {
    
    private List<FichaDomino> listaFichasUsuario ;
    private List<JButton> listaBotonesFichasUsuario ;
    private List<JButton> fichaComparativa ;
    private List<ITableroDominoModeloListener> listeners;
    private Random random = new Random() ;
    
    
    public TableroDominoModel() {
        listaFichasUsuario = new ArrayList() ;
        listaBotonesFichasUsuario = new ArrayList() ;
        fichaComparativa = new ArrayList() ;
        listeners = new ArrayList() ;
    }
    
    public void addListener(ITableroDominoModeloListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ITableroDominoModeloListener listener) {
        listeners.remove(listener);
    }
    
    public void validarExtremoCompatible(FichaDomino ficha) {
        
    }
    
    public void setExtremoListener(ActionListener listener) {
        
    }
    
    public void notificarListaTableroDominoActualizadoListener(){
        
    }

    public List<FichaDomino> getListaFichasUsuario() {
        return listaFichasUsuario;
    }

    public void setListaFichasUsuario(List<FichaDomino> listaFichasUsuario) {
        this.listaFichasUsuario = listaFichasUsuario;
    }

    public List<JButton> getListaBotonesFichasUsuario() {
        return listaBotonesFichasUsuario;
    }

    public void setListaBotonesFichasUsuario(List<JButton> listaBotonesFichasUsuario) {
        this.listaBotonesFichasUsuario = listaBotonesFichasUsuario;
    }

    public List<JButton> getFichaComparativa() {
        return fichaComparativa;
    }

    public void setFichaComparativa(List<JButton> fichaComparativa) {
        this.fichaComparativa = fichaComparativa;
    }
    
    public void repartirFichas() {
        for (int i = 0; i < 5; i++) {
            int extremo1 = random.nextInt(6) + 1;
            int extremo2 = random.nextInt(6) + 1;
            listaFichasUsuario.add(new FichaDomino(extremo1, extremo2));
        }
        notifyListaFichasUsuarioChanged();
    }
    
    private void notifyListaFichasUsuarioChanged() {
        for (ITableroDominoModeloListener listener : listeners) {
            listener.onChangeListaFichasUsuario(listaFichasUsuario);
        }
    }

    private void notifyListaBotonesFichasUsuarioChanged() {
        for (ITableroDominoModeloListener listener : listeners) {
            listener.onChangeListaBotonesFichasUsuario(listaBotonesFichasUsuario);
        }
    }

    private void notifyFichaComparativaChanged() {
        for (ITableroDominoModeloListener listener : listeners) {
            if (!fichaComparativa.isEmpty()) {
                listener.onChangeFichaComparativa(fichaComparativa.get(0));
            }
        }
    }
    
    
}
