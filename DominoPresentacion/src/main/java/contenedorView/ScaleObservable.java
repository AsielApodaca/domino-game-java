/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package contenedorView;

/**
 *
 * @author asielapodaca
 */
public interface ScaleObservable {
    void addScaleObserver(ScaleObserver observer);
    void removeScaleObserver(ScaleObserver observer);
    void notifyScaleObservers();
}
