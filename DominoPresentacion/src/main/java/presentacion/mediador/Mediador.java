/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.mediador;

import contenedorpantallas.FormContenedorController;
import contenedorpantallas.IContenidoController;
import dominodto.FichaDominoDTO;
import java.util.List;
import javax.swing.JPanel;
import presentacion.partidadomino.PartidaDominoModel;
import presentacion.partidadomino.fichadomino.FichaDominoModel;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class Mediador implements IMediador {

    private static Mediador instance;
    private FormContenedorController contenedorController;
    private IContenidoController contenidoController;
    private JPanel contenedorPanel;
    
    private PartidaDominoModel partidaDomino;
    private List<FichaDominoModel> fichaModels;

    private Mediador() {
    }
    
    public static Mediador getInstance() { // Retorna una instancia estática de si mismo
        if (instance == null) {
            instance = new Mediador();
        }
        return instance;
    }

    @Override
    public void crearFichasJugadorLocal() {
        List<FichaDominoDTO> fichas = partidaDomino.getListaFichasJugadorLocal();
        for (FichaDominoDTO ficha : fichas) {
            FichaDominoModel model = new FichaDominoModel(ficha);
            fichaModels.add(model);
        }
    }

    @Override
    public List<FichaDominoModel> obtenerFichasJugadorLocal() {
        return fichaModels;
    }

    @Override
    public void redimencionarFichasJugadorLocal() {
        float escala = partidaDomino.getEscala();
        int anchoFicha = (int) (partidaDomino.getAnchoFichaJugadorLocal() * escala);
        int altoFicha = (int) (partidaDomino.getAlturaFichaJugadorLocal() * escala);

        for (FichaDominoModel model : fichaModels) {
            model.setDimensiones(anchoFicha, altoFicha);
        }
    }
    
    // Inicio de navegación entre pantallas y notificación de cambio de escala
    
    @Override
    public void setContenedorController(FormContenedorController contenedorController) {
        this.contenedorController = contenedorController;
        setContenedorPanel();
    }
    
    private void setContenedorPanel() {
        this.contenedorPanel = contenedorController.getContenedorPanel();
    }
    
    @Override
    public void setContenidoController(IContenidoController contenidoController) {
        this.contenidoController = contenidoController;
        notificarEscalaAContenido();
    }
    
    @Override
    public void notificarEscalaAContenido() {
        if(contenedorController != null && contenidoController != null) {
            this.contenidoController.actualizarEscala(contenedorController.getEscala());
        }
    }
    
    @Override
    public void mostrarPantalla() {
        if (contenedorPanel != null) {
            contenedorPanel.removeAll();
            contenedorPanel.add(contenidoController.obtenerView());
            contenedorPanel.revalidate();
            contenedorPanel.repaint();
        }
    }
    
    // Fin de navegación entre pantallas y notificación de cambio de escala

}
