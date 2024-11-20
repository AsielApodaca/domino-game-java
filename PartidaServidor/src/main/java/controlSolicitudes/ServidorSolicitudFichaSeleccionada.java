package controlSolicitudes;

import adapterEntidades.AdapterFichaDomino;
import dominio.FichaDominoEntity;
import dominio.SalaEntity;
import dominio.TableroDominoEntity;
import domino.solicitudes.EventoSolicitud;
import domino.solicitudes.SolicitudFichaSeleccionada;
import dominodto.FichaDominoDTO;
import java.util.List;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class ServidorSolicitudFichaSeleccionada extends SolicitudManejador {
    private AdapterFichaDomino adapterFicha;
    private TableroDominoEntity tableroDominoEntity;
    private SalaEntity salaEntity;
    


    public ServidorSolicitudFichaSeleccionada() {
        this.adapterFicha = new AdapterFichaDomino();
        this.tableroDominoEntity = new TableroDominoEntity();
        this.salaEntity = new SalaEntity();
    }

    
    /**
     * Procesa una solicitud de tipo SolicitudFichaSeleccionada o la
     * delega al siguiente manejador en la cadena de responsabilidad.
     *
     * @param solicitud la solicitud a procesar, una instancia de
     * manejador.
     */
    @Override
    public void manejarSolicitud(EventoSolicitud solicitud) {
        if (solicitud instanceof SolicitudFichaSeleccionada solicitudFichaSeleccionada) {
            onFichaSeleccionada(solicitudFichaSeleccionada);
        } else if (siguiente != null) {
            siguiente.manejarSolicitud(solicitud);
        }
    }
    
          
    public void onFichaSeleccionada(SolicitudFichaSeleccionada eventoSolicitud) {
        FichaDominoDTO fichaSeleccionadaDTO = eventoSolicitud.getFichaDominoDTO();
        if (fichaSeleccionadaDTO == null) {
            this.tableroDominoEntity.setFichaSeleccionada(null);
            ocultarPosiblesMovimientos();
        } else {
            List<FichaDominoEntity> listaFichasJugador = salaEntity.getJugadorLocal().getListaFichasJugador();
            FichaDominoEntity fichaSeleccionadaEntity
                    = // compara la fichaDTO con las fichas del jugadorLocal
                    estrategiaComparadorEntidades.comparar(
                            fichaSeleccionadaDTO, listaFichasJugador
                    );
            this.tableroDominoEntity.setFichaSeleccionada(fichaSeleccionadaEntity);
            System.out.println(tableroDominoEntity.getFichaSeleccionada() == null);
            mostrarPosiblesMovimientos();
        }
    }

    
}
