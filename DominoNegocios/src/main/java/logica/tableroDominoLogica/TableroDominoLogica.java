/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.tableroDominoLogica;

import adapterEntidades.AdapterFichaDomino;
import adapterEntidades.AdapterJugadorDomino;
import adapterEntidades.IAdapterFichaDomino;
import adapterEntidades.IAdapterJugadorDomino;
import dominio.CasillaEntity;
import dominio.ConfiguracionJuegoEntity;
import dominio.FichaDominoEntity;
import dominio.JugadorDominoEntity;
import dominio.PozoEntity;
import dominio.SalaEntity;
import dominio.TableroDominoEntity;
import dominodto.CasillaDTO;
import dominodto.FichaDominoDTO;
import dominodto.JugadorDominoDTO;
import dominodto.TableroDominoDTO;
import java.util.ArrayList;
import java.util.List;
import listeners.ITableroDominoLogicaListener;
import logica.controladorFichas.ControladorFichasLogica;
import logica.controladorFichas.IControladorFichasLogica;
import logica.controladorTurno.ControladorTurno;
import logica.controladorTurno.IControladorTurno;
import mapeodto.MapeadorDTO;
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
    private MapeadorDTO mapeadorDTO;
    private List<JugadorDominoDTO> jugadoresDTO = new ArrayList<>();

    public TableroDominoLogica(SalaEntity salaEntity) {
        this.salaEntity = salaEntity;
        this.fachadaPartidaDomino = new FachadaPartidaDomino();
        this.tableroDominoEntity = new TableroDominoEntity();
        this.adapterJugadorDomino = new AdapterJugadorDomino();
        this.adapterFichaDomino = new AdapterFichaDomino();
        this.controladorFichas = new ControladorFichasLogica();
        this.controladorTurno = new ControladorTurno();
        this.mapeadorDTO = new MapeadorDTO();

    }

    @Override
    public void iniciar() {
        mostrarPresentacionPartida();
        crearPozoDeFichas();
        prepararTableroDeFichas();
        repartirFichasAJugadores();
        asignarTurnosAJugadores();
        mostrarFichasJugadorLocal();

        // Se colocará este metodo cuando el mvc ya tenga listeners
    }

    private void mostrarPresentacionPartida() {
        fachadaPartidaDomino.iniciarPantalla();
        fachadaPartidaDomino.escucharFichaSeleccionada(this);
    }
    
    private void prepararTableroDeFichas() {
        controladorFichas.setTableroDomino(tableroDominoEntity);
    }

    private void repartirFichasAJugadores() {
        int fichasPorJugador = this.salaEntity.getConfiguracionPartida().getFichasPorJugador();
        for (JugadorDominoEntity jugadorEntity : salaEntity.getListaJugadores()) {
            List<FichaDominoEntity> setDeFichas = controladorFichas.repartirFichas(fichasPorJugador);
            jugadorEntity.setListaFichasJugador(setDeFichas);
        }
    }

    private void asignarTurnosAJugadores() {
        controladorTurno.setJugadores(salaEntity.getListaJugadores());
        controladorTurno.asignarTurnos();
    }

    public void mostrarFichasJugadorLocal() {
        List<FichaDominoEntity> fichasJugadorLocal = salaEntity.getJugadorLocal().getListaFichasJugador();
        List<FichaDominoEntity> fichasCompatibles = controladorFichas.obtenerFichasCompatibles(fichasJugadorLocal);
        List<FichaDominoEntity> fichasNoCompatibles = controladorFichas.obtenerFichasNoCompatibles(fichasJugadorLocal);
        List<FichaDominoDTO> fichasJugadorLocalDTO = new ArrayList<>();
        
        for(FichaDominoEntity ficha : fichasCompatibles) {
            FichaDominoDTO fichaDTO = new FichaDominoDTO(ficha.getExtremo1(), ficha.getExtremo2());
            fichaDTO.setCompatible(true);
            fichasJugadorLocalDTO.add(fichaDTO);
        }
        for(FichaDominoEntity ficha : fichasNoCompatibles) {
            FichaDominoDTO fichaDTO = new FichaDominoDTO(ficha.getExtremo1(), ficha.getExtremo2());
            fichaDTO.setCompatible(false);
            fichasJugadorLocalDTO.add(fichaDTO);
        }
        fachadaPartidaDomino.mostrarFichasJugadorLocal(fichasJugadorLocalDTO);
    }

    private void crearPozoDeFichas() {
        PozoEntity pozo = new PozoEntity();
        controladorFichas.setPozo(pozo);
    }

//    private void simularListaFichasDTO() { // temporal
//        List<FichaDominoDTO> listaFichas = new ArrayList<>();
//
//        listaFichas.add(new FichaDominoDTO(6, 6));
//        listaFichas.add(new FichaDominoDTO(1, 6));
//        listaFichas.add(new FichaDominoDTO(1, 4));
//        listaFichas.add(new FichaDominoDTO(4, 4));
//        listaFichas.add(new FichaDominoDTO(4, 5));
//        listaFichas.add(new FichaDominoDTO(3, 4));
//        listaFichas.add(new FichaDominoDTO(3, 0));
//
//        fachadaPartidaDomino.mostrarFichasJugadorLocal(listaFichas);
//
//    }

    @Override
    public void onFichaSeleccionadaChange(FichaDominoDTO fichaSeleccionada) {
        if(fichaSeleccionada == null) {
            this.tableroDominoEntity.setFichaSeleccionada(null);
            ocultarPosiblesMovimientos();
        } else {
            this.tableroDominoEntity.setFichaSeleccionada(this.adapterFichaDomino.adaptToEntity(fichaSeleccionada));
            mostrarPosiblesMovimientos();
        }
        
    }
    
    private void ocultarPosiblesMovimientos() {
        
    }

    private void mostrarPosiblesMovimientos() {
        FichaDominoEntity fichaSeleccionada = tableroDominoEntity.getFichaSeleccionada();
        if (fichaSeleccionada == null) {
            return;
        }
        List<CasillaDTO> posiblesCasillasColocables = new ArrayList<>();
        if (tableroDominoEntity.getValorExtremo1() == -1
                && tableroDominoEntity.getValorExtremo2() == -1) {
            CasillaDTO casillaMula = mapeadorDTO.casillaEntityADTO(tableroDominoEntity.obtenerPosibleCasillaMula());
            casillaMula.setExtremo(casillaMula.MULA);
            posiblesCasillasColocables.add(casillaMula);
        } else {
            // Verificar si la ficha se puede colocar en el extremo 1
            if (puedeColocarEnExtremo(fichaSeleccionada, tableroDominoEntity.getValorExtremo2())) {
                CasillaDTO casillaExtremo1 = mapeadorDTO.casillaEntityADTO(tableroDominoEntity.obtenerPosibleCasillaExtremo1());
                casillaExtremo1.setExtremo(casillaExtremo1.EXTREMO1);
                posiblesCasillasColocables.add(casillaExtremo1);
            }
            // Verificar si la ficha se puede colocar en el extremo 2
            if (puedeColocarEnExtremo(fichaSeleccionada, tableroDominoEntity.getValorExtremo2())) {
                CasillaDTO casillaExtremo2 = mapeadorDTO.casillaEntityADTO(tableroDominoEntity.obtenerPosiblePosicionExtremo2());
                casillaExtremo2.setExtremo(casillaExtremo2.EXTREMO2);
                posiblesCasillasColocables.add(casillaExtremo2);
            }
        }
        fachadaPartidaDomino.mostrarCasillasParaColocarFicha(posiblesCasillasColocables);
    }

    private boolean puedeColocarEnExtremo(FichaDominoEntity ficha, int valorExtremo) {
        return ficha.getExtremo1() == valorExtremo || ficha.getExtremo2() == valorExtremo;
    }

}
