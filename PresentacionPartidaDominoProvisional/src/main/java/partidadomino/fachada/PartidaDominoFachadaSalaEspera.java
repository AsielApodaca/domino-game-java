/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package partidadomino.fachada;

import listeners.IContenedorListener;
import partidadomino.mvc.PartidaDominoController;
import partidadomino.mvc.PartidaDominoView;

/**
 *
 * @author castr
 */
public class PartidaDominoFachadaSalaEspera {

    public IContenedorListener iniciar() {
        PartidaDominoView view = new PartidaDominoView();
        PartidaDominoController controller = new PartidaDominoController(view);
        return controller;
    }
}
