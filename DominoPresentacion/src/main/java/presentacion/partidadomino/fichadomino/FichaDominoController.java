/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.partidadomino.fichadomino;

import dominodto.FichaDominoDTO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private IMediador mediador;

    public FichaDominoController() {
        this.fichaDominoModel = new FichaDominoModel();
        try {
            this.view = new FichaDominoView(fichaDominoModel);
            addMouseListenerToView();
        } catch (IOException ex) {
            Logger.getLogger(FichaDominoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void mostrarFichas(List<FichaDominoDTO> fichasJugador){
        for(FichaDominoDTO ficha : fichasJugador){
            fichaDominoModel.asignarExtremos(ficha);
        }
    } 
     
    
    public void crearFicha(){
        
    }
    
    public void addMouseListenerToView() {
        this.view.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mediador.notificarFichaSeleccionada(fichaDominoModel.getFichaDominoDTO());
            }
        });
    }

}
