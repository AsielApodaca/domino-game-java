/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.partidadomino.fichadominojugador;

import dominodto.FichaDominoDTO;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import presentacion.mediador.IMediador;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class FichaDominoController {
    private FichaDominoModel fichaDominoModel;
    private FichaDominoView view;

    public FichaDominoController(FichaDominoView view, FichaDominoModel model) {
        this.fichaDominoModel = new FichaDominoModel();
        try {
            this.view = new FichaDominoView(fichaDominoModel);
        } catch (IOException ex) {
            Logger.getLogger(FichaDominoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public FichaDominoModel getFichaDominoModel() {
        return fichaDominoModel;
    }

    public FichaDominoView getView() {
        return view;
    }
    
    
}
