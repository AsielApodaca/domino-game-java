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
import dominio.SalaEntity;
import dominio.TableroDominoEntity;
import dominodto.FichaDominoDTO;
import dominodto.JugadorDominoDTO;
import java.util.ArrayList;
import java.util.List;
import listeners.ITableroDominoLogicaListener;
import logica.controladorFichas.ControladorFichasLogica;
import logica.controladorFichas.IControladorFichasLogica;
import logica.controladorTurno.ControladorTurno;
import logica.controladorTurno.IControladorTurno;
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
    private IControladorFichasLogica controladorFichas;
    private IControladorTurno controladorTurno;
    private JugadorDominoDTO jugadorLocalDTO;
    private SalaEntity salaEntity;
    private TableroDominoEntity tableroDominoEntity;
    private IAdapterFichaDomino adapterFichaDomino;
    private IAdapterJugadorDomino adapterJugadorDomino;
    private List<JugadorDominoDTO> jugadoresDTO = new ArrayList<>();
 

    public TableroDominoLogica(SalaEntity salaEntity) {
        this.salaEntity = salaEntity;
        System.out.println(salaEntity.getConfiguracionPartida().getFichasPorJugador());
        this.fachadaPartidaDomino = new FachadaPartidaDomino();
        this.tableroDominoEntity = new TableroDominoEntity();
        this.adapterJugadorDomino = new AdapterJugadorDomino();
        this.adapterFichaDomino = new AdapterFichaDomino();
        this.controladorFichas = new ControladorFichasLogica();
        this.controladorTurno = new ControladorTurno();

    }

    @Override
    public void iniciar() {
        crearPresentacionPartida();
        crearPozoDeFichas();
        repartirFichasAJugadores();
        asignarTurnosAJugadores();
        obtenerJugadorLocal();
        mostrarFichas();
//        mostrarFichas();
        
        // Se colocará este metodo cuando el mvc ya tenga listeners
    }

    private void crearPresentacionPartida() {
        fachadaPartidaDomino.iniciarPantalla();
    }

    private void repartirFichasAJugadores() {
        int fichasPorJugador = this.salaEntity.getConfiguracionPartida().getFichasPorJugador();
        for(JugadorDominoEntity jugadorEntity : salaEntity.getListaJugadores()) {
            List<FichaDominoEntity> setDeFichas = controladorFichas.repartirFichas(fichasPorJugador);
            jugadorEntity.setListaFichasJugador(setDeFichas);
        }
    }
    private void obtenerJugadorLocal(){    
        this.jugadorLocalDTO = adapterJugadorDomino.adaptToDTO(salaEntity.getJugadorLocal());
    }          
    
    private void asignarTurnosAJugadores() {
        controladorTurno.setJugadores(salaEntity.getListaJugadores());
        controladorTurno.asignarTurnos();
    }
    
            

    public void mostrarFichas() {
        fachadaPartidaDomino.mostrarFichasJugadorLocal(jugadorLocalDTO.getListaFichasJugador());
    }

    private void crearPozoDeFichas() {
        PozoEntity pozo = new PozoEntity();
        controladorFichas.setPozo(pozo);
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
