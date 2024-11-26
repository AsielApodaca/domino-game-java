/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package partidadomino.mvc;

import javax.swing.JPanel;
import listeners.IContenedorListener;
import listeners.SalaEspera.IPresentacionSalaEsperaListener;
import listeners.mvcview.IViewListener;

/**
 *
 * @author castr
 */
public class PartidaDominoController implements IContenedorListener, IViewListener{

    private PartidaDominoView view;
    private IPresentacionSalaEsperaListener presentacionSalaEsperaListener;

    public PartidaDominoController(PartidaDominoView view) {
        this.view = view;
    }

    @Override
    public void onEscalaChange(float escala) {
        //Nadota
    }

    @Override
    public JPanel obtenerView() {
        return view;
    }

    @Override
    public void onBtnEmpezarPartidaPrecionado() {
        presentacionSalaEsperaListener.onBtnIniciarPartidaPrecionado();
    }
    
    public void suscribirSalaEsperaListener(IPresentacionSalaEsperaListener presentacionSalaEsperaListener) {
        this.presentacionSalaEsperaListener = presentacionSalaEsperaListener;
    }

}
