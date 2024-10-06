/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contenedorView;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author asielapodaca
 */
public class FormContenedorModel implements ScaleProvider, ScaleObservable{    
    
    private List<ScaleObserver> observers = new ArrayList<>(); // Observers de escala
    
    private int anchoFrame; // Ancho actual de FormContenedorView
    private int alturaFrame; // Alto actual de FormContenedorView
    
    private int alturaMinimaFrame; // Altura mínima de FormContenedorView
    private int alturaMinimaFrameAjustado; // Altura mínima de FormContenedorView ajustada con base a la barra de la ventana
    private int anchoMinimoFrame; // Ancho mínimo de FormContenedorView
    
    private int alturaBarraTituloVentana; // Altura de la barra de título del JFrame
    private int alturaUtilFrame; // Altura de FormContenedorView no cubierta por la barra de la ventana
    
    private int anchoContenedor; // Ancho actual del contenedor
    private int alturaContenedor; // Altura actual del contenedor
    private int locacionContenedorX; // posición de contenedor en el eje X
    private int locacionContenedorY; // posición de contenedor en el eje Y
    
    // Relación de aspecto de la altura del contenedor con base a su ancho
    // Por ejemplo, 2/3, la altura será 2/3 del largo del ancho
    private float relacionAlturaAncho;
    
    private float escala; // Proporción del contenedor conforme a sus medida base/original
    
    private JPanel contenedorPanel; // Panel que contendrá pantallas del juego
    
    private Color frameBrackgroundColor; // Color de fondo del Frame
    private Color contenedorBrackgroundColor; // Color de fondo del contenedor

    public FormContenedorModel() {
        this.contenedorPanel = new JPanel();
        this.anchoMinimoFrame = 600;
        this.alturaMinimaFrame = 400;
        this.relacionAlturaAncho = 2.0f / 3.0f;
        
        this.frameBrackgroundColor = Color.BLACK;
        this.contenedorBrackgroundColor = Color.MAGENTA;
    }

    public Color getFrameBrackgroundColor() {
        return frameBrackgroundColor;
    }

    public Color getContenedorBrackgroundColor() {
        return contenedorBrackgroundColor;
    }

    
    
    public JPanel getContenedorPanel() {
        return contenedorPanel;
    }
    
    public int getAnchoFrame() {
        return anchoFrame;
    }

    public void setAnchoFrame(int anchoFrame) {
        this.anchoFrame = anchoFrame;
    }

    public int getAlturaFrame() {
        return alturaFrame;
    }

    public void setAlturaFrame(int alturaFrame) {
        this.alturaFrame = alturaFrame;
    }

    public int getAlturaMinimaFrame() {
        return alturaMinimaFrame;
    }

    public int getAlturaMinimaFrameAjustado() {
        return alturaMinimaFrameAjustado;
    }

    public void setAlturaMinimaFrameAjustado(int alturaMinimaFrameAjustado) {
        this.alturaMinimaFrameAjustado = alturaMinimaFrameAjustado;
    }

    public int getAnchoMinimoFrame() {
        return anchoMinimoFrame;
    }

    public void setAnchoMinimoFrame(int anchoMinimoFrame) {
        this.anchoMinimoFrame = anchoMinimoFrame;
    }

    public int getAlturaBarraTituloVentana() {
        return alturaBarraTituloVentana;
    }

    public void setAlturaBarraTituloVentana(int alturaBarraTituloVentana) {
        this.alturaBarraTituloVentana = alturaBarraTituloVentana;
    }

    public int getAlturaUtilFrame() {
        return alturaUtilFrame;
    }

    public void setAlturaUtilFrame(int alturaUtilFrame) {
        this.alturaUtilFrame = alturaUtilFrame;
    }

    public int getAnchoContenedor() {
        return anchoContenedor;
    }

    public void setAnchoContenedor(int anchoContenedor) {
        this.anchoContenedor = anchoContenedor;
    }

    public int getAlturaContenedor() {
        return alturaContenedor;
    }

    public void setAlturaContenedor(int alturaContenedor) {
        this.alturaContenedor = alturaContenedor;
    }

    public int getLocacionContenedorX() {
        return locacionContenedorX;
    }

    public void setLocacionContenedorX(int locacionContenedorX) {
        this.locacionContenedorX = locacionContenedorX;
    }

    public int getLocacionContenedorY() {
        return locacionContenedorY;
    }

    public void setLocacionContenedorY(int locacionContenedorY) {
        this.locacionContenedorY = locacionContenedorY;
    }

    public float getRelacionAlturaAncho() {
        return relacionAlturaAncho;
    }

    public void setRelacionAlturaAncho(float relacionAlturaAncho) {
        this.relacionAlturaAncho = relacionAlturaAncho;
    }

    public void setEscala(float escala) {
        if (this.escala != escala) {
            this.escala = escala;
            notifyScaleObservers();
        }
    }

    @Override
    public float getScale() {
        return this.escala;
    }

    @Override
    public void addScaleObserver(ScaleObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeScaleObserver(ScaleObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyScaleObservers() {
        for (ScaleObserver observer : observers) {
            observer.onScaleChanged(this.escala);
        }
    }
    
    
    
    
}
