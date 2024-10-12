/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.partidadomino.fichadominojugador;

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
                mediador.notificarFichaSeleccionada(model.getFichaDominoDTO());
            }
        });
    }

    public void actualizarEscala(float escala) {
        model.setEscala(escala);
        view.repintar();
    }
    
}
