package fichabuilder;

import dominodto.FichaDominoDTO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import presentacion.partidadomino.fichadominojugador.FichaDominoController;
import presentacion.partidadomino.fichadominojugador.FichaDominoModel;
import presentacion.partidadomino.fichadominojugador.FichaDominoView;

public class FichaBuilderUsuario implements IFichaBuilder {
    
    private FichaDominoModel fichaDominoModel;
    private FichaDominoView fichaDominoView;
    private FichaDominoController fichaDominoController;
    
    @Override
    public void asignarExtremos(FichaDominoDTO ficha) {
        fichaDominoModel.setValorExtremo1(ficha.getValorExtremo1());
        fichaDominoModel.setValorExtremo2(ficha.getValorExtremo2());
    }
     
    @Override
    public void cargarImagenesFicha(int extremo1, int extremo2) {
        fichaDominoModel.setImgExtremo1(String.format("/multimedia/Domino%d.png", extremo1));
        fichaDominoModel.setImgExtremo2(String.format("/multimedia/Domino%d.png", extremo2));
    }

    public FichaDominoController construirFicha(FichaDominoDTO fichaDTO) throws IOException {
        try {
            fichaDominoModel = new FichaDominoModel(fichaDTO);
            fichaDominoView = new FichaDominoView(fichaDominoModel);
            fichaDominoController = new FichaDominoController(fichaDominoModel, fichaDominoView);
 
            fichaDominoModel.setFichaDominoDTO(fichaDTO);
            asignarExtremos(fichaDTO);
            cargarImagenesFicha(fichaDominoModel.getValorExtremo1(), fichaDominoModel.getValorExtremo2());
            fichaDominoView.cargarComponentes();
        } catch (IOException ex) {
            Logger.getLogger(FichaBuilderUsuario.class.getName()).log(Level.SEVERE, "Error al construir la ficha", ex);
            throw ex; // Re-throw the exception to be handled by the caller
        }
        
        return fichaDominoController;
    }
}