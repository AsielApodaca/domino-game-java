/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.tableroDominoLogica;

import adapterEntidades.AdapterFichaDomino;
import adapterEntidades.AdapterJugadorDomino;
import adapterEntidades.IAdapterFichaDomino;
import adapterEntidades.IAdapterJugadorDomino;
import dominio.ConfiguracionJuegoEntity;
import dominio.FichaDominoEntity;
import dominio.JugadorDominoEntity;
import dominio.PozoEntity;
import dominio.TableroDominoEntity;
import dominodto.FichaDominoDTO;
import dominodto.JugadorDominoDTO;
import java.util.ArrayList;
import java.util.List;
import listeners.ITableroDominoLogicaListener;
import logica.controladorFichas.ControladorFichasLogica;
import logica.controladorFichas.IControladorFichasLogica;
import presentacion.partidadomino.fachada.FachadaPartidaDomino;
import presentacion.partidadomino.fachada.IFachadaPartidaDomino;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class TableroDominoLogica implements ITableroDominoLogica, ITableroDominoLogicaListener {

    private IFachadaPartidaDomino fachadaPartidaDomino;
    private IControladorFichasLogica controladorFicha;
    private PozoEntity pozo;
    private JugadorDominoDTO jugadorDominoDTO;

    private List<FichaDominoDTO> fichasRepartidasDTO;
    private TableroDominoEntity tableroDominoEntity;
    private List<JugadorDominoEntity> jugadoresEntity;
    private IAdapterFichaDomino adapterFichaDomino;
    private IAdapterJugadorDomino adapterJugadorDomino;
    private List<JugadorDominoDTO> jugadoresDTO = new ArrayList<>();
 

    public TableroDominoLogica(ConfiguracionJuegoEntity configuracionEntity) {
        this.fachadaPartidaDomino = new FachadaPartidaDomino();
        this.tableroDominoEntity = new TableroDominoEntity();
        this.adapterJugadorDomino = new AdapterJugadorDomino();
        this.fichasRepartidasDTO = new ArrayList<>();
        this.adapterFichaDomino = new AdapterFichaDomino();
        this.jugadoresEntity = tableroDominoEntity.getListaJugadores();
        crearPozo();
        controladorFicha = new ControladorFichasLogica(pozo);
        repartirFichasJugador(configuracionEntity.getCantidadFichas());
        asignarJugadorLocal();

    }

    @Override
    public void iniciar() {
        crearPresentacionPartida();
        simularListaFichasDTO();
//        mostrarFichas();
        
        // Se colocará este metodo cuando el mvc ya tenga listeners
    }

    private void crearPresentacionPartida() {
        fachadaPartidaDomino.iniciarPantalla();
    }

    private void repartirFichasJugador(int cantidadFichas) {
        try {
            for (JugadorDominoEntity jugadorEntity : jugadoresEntity) {
                // Repartir fichas para el jugador actual
                List<FichaDominoEntity> fichasRepartidasEntity = controladorFicha.repartirFichas(cantidadFichas);
                jugadorEntity.setListaFichasJugador(fichasRepartidasEntity);

            }
        } catch (Exception e) {
            e.printStackTrace(); // Mostrar el tipo de excepción y su stack trace
        }
    }
    private void asignarJugadorLocal(){    
        jugadorDominoDTO = adapterJugadorDomino.adaptToDTO(jugadoresEntity.getFirst());
    }          
    
    
    
            

    public void mostrarFichas() {
        fachadaPartidaDomino.mostrarFichasJugadorLocal(jugadorDominoDTO.getListaFichasJugador());
    }

    private void crearPozo() {
        pozo = new PozoEntity();
    }

    private void simularListaFichasDTO() { // temporal
        List<FichaDominoDTO> listaFichas = new ArrayList<>();

        listaFichas.add(new FichaDominoDTO(6, 6));
        listaFichas.add(new FichaDominoDTO(1, 6));
        listaFichas.add(new FichaDominoDTO(1, 4));
        listaFichas.add(new FichaDominoDTO(4, 4));
        listaFichas.add(new FichaDominoDTO(4, 5));
        listaFichas.add(new FichaDominoDTO(3, 4));
        listaFichas.add(new FichaDominoDTO(3, 0));

        fachadaPartidaDomino.mostrarFichasJugadorLocal(listaFichas);

    }

    @Override
    public void onFichaSeleccionadaChange(FichaDominoDTO fichaSeleccionada) {
        this.tableroDominoEntity.setFichaSeleccionada(this.adapterFichaDomino.adaptToEntity(fichaSeleccionada));
    }

}
