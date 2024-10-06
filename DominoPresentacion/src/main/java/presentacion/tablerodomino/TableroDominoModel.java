/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.tablerodomino;

import dominio.FichaDomino;
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

    private List<FichaDomino> listaFichasUsuario;
    private List<FichaDomino> fichasComparativas;
    private List<ITableroDominoModeloListener> listeners;
    private List<FichaDomino> listaFichasValidas;
    private FichaDomino fichaComparativa;
    private FichaDomino fichaSeleccionada;
    private Random random = new Random();

    public TableroDominoModel() {
        listaFichasUsuario = new ArrayList<>();
        listeners = new ArrayList<>();
        fichasComparativas = new ArrayList<>() ;
        listaFichasValidas = new ArrayList<>() ;
        simularFichasComparativas();
    }
    
    private void simularFichasComparativas() {
        listaFichasUsuario.clear();
        for (int i = 0; i < 5; i++) {
            int extremo1 = random.nextInt(6) + 1;
            int extremo2 = random.nextInt(6) + 1;
            fichasComparativas.add(new FichaDomino(extremo1, extremo2));
        }
    }

    public void addListener(ITableroDominoModeloListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ITableroDominoModeloListener listener) {
        listeners.remove(listener);
    }

    public void validarExtremoCompatible(FichaDomino ficha) {

    }

    public void notificarListaTableroDominoActualizadoListener() {

    }

    public List<FichaDomino> getListaFichasUsuario() {
        return listaFichasUsuario;
    }

    public void setListaFichasUsuario(List<FichaDomino> listaFichasUsuario) {
        this.listaFichasUsuario = listaFichasUsuario;
        notifyListaFichasUsuarioChanged();
    }

    public void repartirFichas() {
        listaFichasUsuario.clear();
        for (int i = 0; i < 5; i++) {
            int extremo1 = random.nextInt(6) + 1;
            int extremo2 = random.nextInt(6) + 1;
            listaFichasUsuario.add(new FichaDomino(extremo1, extremo2));
        }
        notifyListaFichasUsuarioChanged();

        if (!listaFichasUsuario.isEmpty()) {
            setFichaComparativa(listaFichasUsuario.get(random.nextInt(listaFichasUsuario.size())));
        }
    }

    private void notifyListaFichasUsuarioChanged() {
        for (ITableroDominoModeloListener listener : listeners) {
            listener.onChangeListaFichasUsuario(listaFichasUsuario);
        }
    }

    private void notifyFichaComparativaChanged() {
        for (ITableroDominoModeloListener listener : listeners) {
            listener.onChangeFichaComparativa(fichaComparativa);
        }
    }
    
    private void notifyFichaSeleccionadaChanged() {
        for (ITableroDominoModeloListener listener : listeners) {
            listener.onChangeFichaSeleccionada(fichaSeleccionada);
        }
    }
    
    private void notifyFichasComparativasChanged() {
        for (ITableroDominoModeloListener listener : listeners) {
            listener.onChangeFichasComparativas(fichasComparativas);
        } 
    }

    public List<FichaDomino> getFichasComparativas() {
        return fichasComparativas;
    }
    
    public void colocarFichaComparativa(FichaDomino fichaComparativa) {
        this.fichasComparativas.add(fichaComparativa) ;
        notifyFichasComparativasChanged() ;
    }

    public void setFichasComparativas(List<FichaDomino> fichasComparativas) {
        this.fichasComparativas = fichasComparativas;
        notifyFichasComparativasChanged() ;
    }

    public FichaDomino getFichaComparativa() {
        return fichaComparativa;
    }

    public void setFichaComparativa(FichaDomino fichaComparativa) {
        this.fichaComparativa = fichaComparativa;
    }

    public List<FichaDomino> getListaFichasValidas() {
        return listaFichasValidas;
    }

    public void setListaFichasValidas(List<FichaDomino> listaFichasValidas) {
        this.listaFichasValidas = listaFichasValidas;
    }

    public FichaDomino getFichaSeleccionada() {
        return fichaSeleccionada;
    }

    public void setFichaSeleccionada(FichaDomino fichaSeleccionada) {
        this.fichaSeleccionada = fichaSeleccionada;
        notifyFichaSeleccionadaChanged();
    }

    public List<ITableroDominoModeloListener> getListeners() {
        return listeners;
    }

    public void setListeners(List<ITableroDominoModeloListener> listeners) {
        this.listeners = listeners;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

}
