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
import estrategiacomparador.EstrategiaComparadorEntidades;
import estrategiacomparador.IEstrategiaComparadorEntidades;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import listeners.IPresentacionListener;
import logica.controladorFichas.ControladorFichasLogica;
import logica.controladorFichas.IControladorFichasLogica;
import logica.controladorTurno.ControladorTurno;
import logica.controladorTurno.IControladorTurno;
import mapeodto.MapeadorDTO;
import notificaciones.IPresentacionNotificacionesManager;
import notificaciones.PresentacionNotificacionesManager;
import notificaciones.eventos.CasillaSeleccionadaEvento;
import notificaciones.eventos.Evento;
import notificaciones.eventos.FichaSeleccionadaEvento;
import presentacion.partidadomino.fachada.FachadaPartidaDomino;
import presentacion.partidadomino.fachada.IFachadaPartidaDomino;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class TableroDominoLogica implements ITableroDominoLogica, IPresentacionListener {

    private static final Logger LOG = Logger.getLogger(TableroDominoLogica.class.getName());
    private IFachadaPartidaDomino fachadaPartidaDomino;
    private IControladorFichasLogica controladorFichas;
    private IControladorTurno controladorTurno;
    private JugadorDominoDTO jugadorLocalDTO;
    private SalaEntity salaEntity;
    private TableroDominoEntity tableroDominoEntity;
    private IEstrategiaComparadorEntidades estrategiaComparadorEntidades;
    private IAdapterJugadorDomino adapterJugadorDomino;
    private MapeadorDTO mapeadorDTO;
    private List<JugadorDominoDTO> jugadoresDTO = new ArrayList<>();
    private IPresentacionNotificacionesManager presentacionNotificacionesManager;

    public TableroDominoLogica(SalaEntity salaEntity) {
        this.salaEntity = salaEntity;
        this.fachadaPartidaDomino = new FachadaPartidaDomino();
        this.tableroDominoEntity = new TableroDominoEntity();
        this.estrategiaComparadorEntidades = new EstrategiaComparadorEntidades();
        this.adapterJugadorDomino = new AdapterJugadorDomino();
        this.controladorFichas = new ControladorFichasLogica();
        this.controladorTurno = new ControladorTurno();
        this.mapeadorDTO = new MapeadorDTO();
        this.presentacionNotificacionesManager = new PresentacionNotificacionesManager();

    }

    @Override
    public void iniciar() {
        mostrarPresentacionPartida();
        escucharEventosPartidaDomino();
        crearPozoDeFichas();
        prepararTableroDeFichas();
        repartirFichasAJugadores();
        asignarTurnosAJugadores();
        mostrarFichasJugadorLocal();

        // Se colocará este metodo cuando el mvc ya tenga listeners
    }
    
    @Override
    public void onPresentacionCambio(Evento evento) {
        String tipoEvento = evento.getNombreEvento();
        switch(tipoEvento) {
            case "FichaSeleccionadaEvento":
                fichaSeleccionadaEvento((FichaSeleccionadaEvento) evento);
                break;
            case "CasillaSeleccionadaEvento":
                casillaSeleccionadaEvento((CasillaSeleccionadaEvento) evento);
                break;
                
        }
    }
    
    private void fichaSeleccionadaEvento(FichaSeleccionadaEvento evento) {
        FichaDominoDTO fichaSeleccionadaDTO = evento.getFichaSeleccionada();
        if(fichaSeleccionadaDTO == null) {
            this.tableroDominoEntity.setFichaSeleccionada(null);
            ocultarPosiblesMovimientos();
        } else {
            List<FichaDominoEntity> listaFichasJugador = salaEntity.getJugadorLocal().getListaFichasJugador();
            FichaDominoEntity fichaSeleccionadaEntity =  // compara la fichaDTO con las fichas del jugadorLocal
                    estrategiaComparadorEntidades.comparar(
                            fichaSeleccionadaDTO, listaFichasJugador
                    );
            this.tableroDominoEntity.setFichaSeleccionada(fichaSeleccionadaEntity);
            System.out.println(tableroDominoEntity.getFichaSeleccionada() == null);
            mostrarPosiblesMovimientos();
        }
        
    }
    
    private void casillaSeleccionadaEvento(CasillaSeleccionadaEvento evento) {
        CasillaDTO casillaSeleccionada = evento.getCasilla();
        CasillaEntity casilla = colocarFichaSeleccionadaEnTableroEntity(casillaSeleccionada.getExtremo());
        removerFichaAJugador(casilla.getFichaDomino());
        mostrarFichasJugadorLocal();
        ocultarPosiblesMovimientos();
        mostrarFichaEnTablero(casilla);
        
    }
    
    private void mostrarFichaEnTablero(CasillaEntity casillaEntity) {
        CasillaDTO fichaAColocar = mapeadorDTO.casillaEntityADTO(casillaEntity);
        fachadaPartidaDomino.colocarFichaTablero(fichaAColocar);
        System.out.println(tableroDominoEntity.getValorExtremo1() + " " + tableroDominoEntity.getValorExtremo2());
    }
    
    private void removerFichaAJugador(FichaDominoEntity fichaDominoEntity) {
        List<FichaDominoEntity> listaFichasJugadorLocal = salaEntity.getJugadorLocal().getListaFichasJugador();
        controladorFichas.removerFichaLista(listaFichasJugadorLocal, fichaDominoEntity);
    }
    
    private CasillaEntity colocarFichaSeleccionadaEnTableroEntity(int extremoTablero) {
        CasillaEntity casilla;
        switch(extremoTablero) {
            case CasillaDTO.MULA:
                casilla = controladorFichas.colocarMula();
                break;
            case CasillaDTO.EXTREMO1:
                casilla = controladorFichas.colocarFichaExtremo1();
                break;
            case CasillaDTO.EXTREMO2:
                casilla = controladorFichas.colocarFichaExtremo2();
                break;
            default:
                casilla = null;
                LOG.log(Level.SEVERE, "La casilla seleccionada no pertenece a ningun extremo");
        }
        tableroDominoEntity.setFichaSeleccionada(null);
        return casilla;
    }
    
    
    private void escucharEventosPartidaDomino() {
        presentacionNotificacionesManager.setPresentacionListener(this);
        fachadaPartidaDomino.establecerComunicacionConListener((PresentacionNotificacionesManager) presentacionNotificacionesManager);
    }

    private void mostrarPresentacionPartida() {
        fachadaPartidaDomino.iniciarPantalla();
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
    
    private void ocultarPosiblesMovimientos() {
        fachadaPartidaDomino.ocultarCasillasParaColocarFicha();
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
            if (puedeColocarEnExtremo(fichaSeleccionada, tableroDominoEntity.getValorExtremo1())) {
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
