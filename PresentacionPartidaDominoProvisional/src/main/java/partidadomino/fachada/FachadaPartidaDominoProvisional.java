/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package partidadomino.fachada;

import listeners.IContenedorListener;
import listeners.SalaEspera.IPresentacionSalaEsperaListener;
import partidadomino.mvc.PartidaDominoController;
import partidadomino.mvc.PartidaDominoView;

/**
 *
 * @author castr
 */
public class FachadaPartidaDominoProvisional implements IFachadaPartidaDominoProvisional {

    @Override
    public IContenedorListener iniciar(IPresentacionSalaEsperaListener presentacionSalaEsperaListener) {
        PartidaDominoView view = new PartidaDominoView();
        PartidaDominoController controller = new PartidaDominoController(view);
        controller.suscribirSalaEsperaListener(presentacionSalaEsperaListener);
        view.suscribirListener(controller);
        return controller;
    }
}
