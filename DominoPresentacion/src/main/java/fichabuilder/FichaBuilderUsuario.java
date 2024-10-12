/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fichabuilder;

import dominodto.FichaDominoDTO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import presentacion.partidadomino.fichadominojugador.FichaDominoController;
import presentacion.partidadomino.fichadominojugador.FichaDominoModel;
import presentacion.partidadomino.fichadominojugador.FichaDominoView;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class FichaBuilderUsuario implements IFichaBuilder{
    

    private FichaDominoModel fichaDominoModel;
    private FichaDominoView fichaDominoView;
    private FichaDominoController fichaDominoController;
    
    
    @Override
    public void asignarExtremos(FichaDominoDTO ficha){
        fichaDominoModel.setValorExtremo1(ficha.getValorExtremo1());
        fichaDominoModel.setValorExtremo2(ficha.getValorExtremo2());
    }
     
    @Override
     public void cargarImagenesFicha(int extremo1, int extremo2) {
         
        fichaDominoModel.setImagenMargenDomino( "/multimedia/DominoFondo.png");
        fichaDominoModel.setImgExtremo1(String.format("/multimedia/Domino%d.png", extremo1));
        fichaDominoModel.setImgExtremo2( String.format("/multimedia/Domino%d.png", extremo2));
    }

    public FichaDominoController construirFicha(FichaDominoDTO fichaDTO) {
        try {
            fichaDominoModel = new FichaDominoModel ();
             fichaDominoView = new FichaDominoView (
                    fichaDominoModel);
             fichaDominoController = new FichaDominoController(
                    fichaDominoView, 
                    fichaDominoModel);
 
        } catch (IOException ex) {
            Logger.getLogger(FichaBuilderUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        asignarExtremos(fichaDTO);
        cargarImagenesFicha(fichaDominoModel.getValorExtremo1(), fichaDominoModel.getValorExtremo2());
        
        return fichaDominoController;
    }
    
    
    
}
