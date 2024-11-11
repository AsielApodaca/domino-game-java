package mediador;


import dominodto.FichaDominoDTO;
import java.util.List;
import mvc.PartidaDominoController;
import partidadomino.fichadominojugadormvc.FichaDominoController;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class MediadorSingletonPantallaFichasJugador {

    private static MediadorSingletonPantallaFichasJugador instancia; // Instancia única de si mismo
    private PartidaDominoController partidaDominoController; // MVC con el que se comunican las fichas
    private List<FichaDominoController> listaFichasJugadorLocal;
    

    private MediadorSingletonPantallaFichasJugador() {
    }
    
    public static MediadorSingletonPantallaFichasJugador getInstance() {
        if(instancia == null) {
            instancia = new MediadorSingletonPantallaFichasJugador();
        }
        return (MediadorSingletonPantallaFichasJugador) instancia;
    }

    public void redimencionarFichasJugadorLocal(float escala) {
        if(listaFichasJugadorLocal != null)
        for(FichaDominoController fichaController : listaFichasJugadorLocal) {
            fichaController.actualizarEscala(escala);
        }
    }
    
    

    public void notificarFichaSeleccionada(FichaDominoDTO fichaSeleccionada) {
        partidaDominoController.getModel().setFichaSeleccionada(fichaSeleccionada);
    }

    public PartidaDominoController getPartidaDominoController() {
        return partidaDominoController;
    }

    public void setPartidaDominoController(PartidaDominoController partidaDominoController) {
        this.partidaDominoController = partidaDominoController;
    }

    public List<FichaDominoController> getListaFichasJugadorLocal() {
        return listaFichasJugadorLocal;
    }

    public void setListaFichasJugadorLocal(List<FichaDominoController> listaFichasJugadorLocal) {
        this.listaFichasJugadorLocal = listaFichasJugadorLocal;
    }

    public void deseleccionarRestoDeFichas(FichaDominoController fichaSeleccionada) {
        for(FichaDominoController fichaControl : listaFichasJugadorLocal) {
            if(fichaControl != fichaSeleccionada && fichaControl.getFichaDominoModel().isSeleccionada()) {
                fichaControl.deseleccionarFicha();
            }
        }
        
        notificarFichaDeseleccionada();
    }

    public void notificarFichaDeseleccionada() {
        partidaDominoController.getModel().setFichaSeleccionada(null);
    }
    
    

}
