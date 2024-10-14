/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.partidadomino.fichadominojugador;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;
import dominodto.FichaDominoDTO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import presentacion.mediador.IMediador;
import listeners.IPartidaDominoModelListener;
import presentacion.mediador.Mediador;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class FichaDominoController {

    private FichaDominoModel model;
    private FichaDominoView view;
    private Mediador mediador;

    public FichaDominoController(FichaDominoModel model, FichaDominoView view) {
        this.model = model;
        this.view = view;
        addMouseListenerToView();
//        configurarEventoSeleccionFicha();
    }

    public FichaDominoModel getFichaDominoModel() {
        return model;
    }

    public FichaDominoView getView() {
        return view;
    }

    public void addMouseListenerToView() {
        this.view.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                mediador.notificarFichaSeleccionada(model.getFichaDominoDTO());
                if (model.isCompatible()) {
                    model.setSeleccionada(!model.isSeleccionada());
                    if (model.isSeleccionada()) {
                        model.setFondoFicha(model.getFondoSeleccionado());
                    } else {
                        model.setFondoFicha(model.getFondoCompatible());
                    }
                } else {
                    model.setFondoFicha(model.getFondoFichaNormal());
                }

                view.actualizarFondo();
            }
        });
    }

    public void actualizarEscala(float escala) {
        model.setEscala(escala);
        view.repintar();
    }

}
