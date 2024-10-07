/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.tablerodomino;

import dominodto.FichaDominoDTO;
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
    
    private int anchoPanel; // Ancho base del panel
    private int alturaPanel; // Altura base del panel
    
    // Esta escala se multiplicará por todos los valores visuales del contenido
    private float escala; // Proporción con base al tamaño original
    
    private List<FichaDominoDTO> listaFichasUsuario;
    private List<FichaDominoDTO> fichasEnMesa;
    private List<ITableroDominoModeloListener> listeners;
    private List<FichaDominoDTO> listaFichasValidas;
    private FichaDominoDTO fichaComparativa;
    private FichaDominoDTO fichaSeleccionada;
    private Random random = new Random();

    public TableroDominoModel() {
        listaFichasUsuario = new ArrayList<>();
        listeners = new ArrayList<>();
        fichasEnMesa = new ArrayList<>() ;
        listaFichasValidas = new ArrayList<>() ;
        simularFichasComparativas();
    }
    
    private void simularFichasComparativas() {
        listaFichasUsuario.clear();
        for (int i = 0; i < 5; i++) {
            int extremo1 = random.nextInt(6) + 1;
            int extremo2 = random.nextInt(6) + 1;
            fichasEnMesa.add(new FichaDominoDTO(extremo1, extremo2));
        }
    }

    public int getAnchoPanel() {
        return anchoPanel;
    }

    public void setAnchoPanel(int anchoPanel) {
        this.anchoPanel = anchoPanel;
    }

    public int getAlturaPanel() {
        return alturaPanel;
    }

    public void setAlturaPanel(int alturaPanel) {
        this.alturaPanel = alturaPanel;
    }

    public float getEscala() {
        return escala;
    }

    public void setEscala(float escala) {
        this.escala = escala;
    }

    
    
    public void addListener(ITableroDominoModeloListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ITableroDominoModeloListener listener) {
        listeners.remove(listener);
    }

    public void validarExtremoCompatible(FichaDominoDTO ficha) {

    }

    public void notificarListaTableroDominoActualizadoListener() {

    }

    public List<FichaDominoDTO> getListaFichasUsuario() {
        return listaFichasUsuario;
    }

    public void setListaFichasUsuario(List<FichaDominoDTO> listaFichasUsuario) {
        this.listaFichasUsuario = listaFichasUsuario;
        notifyListaFichasUsuarioChanged();
    }

    public void repartirFichas() {
        listaFichasUsuario.clear();
        for (int i = 0; i < 5; i++) {
            int extremo1 = random.nextInt(6) + 1;
            int extremo2 = random.nextInt(6) + 1;
            listaFichasUsuario.add(new FichaDominoDTO(extremo1, extremo2));
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
            listener.onChangeFichasComparativas(fichasEnMesa);
        } 
    }

    public List<FichaDominoDTO> getFichasComparativas() {
        return fichasEnMesa;
    }
    
    public void colocarFichaComparativa(FichaDominoDTO fichaComparativa) {
        this.fichasEnMesa.add(fichaComparativa) ;
        notifyFichasComparativasChanged() ;
    }

    public void setFichasComparativas(List<FichaDominoDTO> fichasComparativas) {
        this.fichasEnMesa = fichasComparativas;
        notifyFichasComparativasChanged() ;
    }

    public FichaDominoDTO getFichaComparativa() {
        return fichaComparativa;
    }

    public void setFichaComparativa(FichaDominoDTO fichaComparativa) {
        this.fichaComparativa = fichaComparativa;
    }

    public List<FichaDominoDTO> getListaFichasValidas() {
        return listaFichasValidas;
    }

    public void setListaFichasValidas(List<FichaDominoDTO> listaFichasValidas) {
        this.listaFichasValidas = listaFichasValidas;
    }

    public FichaDominoDTO getFichaSeleccionada() {
        return fichaSeleccionada;
    }

    public void setFichaSeleccionada(FichaDominoDTO fichaSeleccionada) {
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
