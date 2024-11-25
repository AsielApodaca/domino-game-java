package setup;

import domino.fachada.FachadaServidorProxy;
import domino.fachada.IFachadaServidorProxy;
import generadorrespuestas.GeneradorRespuestas;
import logica.IPartidaServerLogica;
import logica.PartidaServerLogica;
import manejadorsolicitudserverproxy.GestorSolicitudServerProxy;
import manejadorsolicitudserverproxy.ManejadorSolicitudCasillaSeleccionada;
import manejadorsolicitudserverproxy.ManejadorSolicitudFichaSeleccionada;
import manejadorsolicitudserverproxy.ManejadorSolicitudIniciarPartida;

/**
 * Configuración inicial del servidor.
 * 
 * Esta clase implementa el proceso de configuración necesario para inicializar los componentes
 * del servidor, incluyendo lógica de negocio, manejadores de solicitudes, generadores de
 * respuestas y conexión con la fachada del servidor.
 * 
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class Setup implements ISetup {

    private IPartidaServerLogica partidaServerLogica;
    private GestorSolicitudServerProxy gestorSolicitudServerProxy;
    private GeneradorRespuestas generadorRespuestas;
    private IFachadaServidorProxy fachadaServidorProxy;

    /**
     * Inicia el proceso de configuración del sistema.
     */
    @Override
    public void iniciar() {
        iniciarLogica();
        iniciarManejadorSolicitudesServer();
        iniciarGeneradorDeRespuestasServer();
        iniciarConexionServer();
    }

    /**
     * Inicializa la lógica de negocio del servidor de partidas.
     */
    private void iniciarLogica() {
        partidaServerLogica = new PartidaServerLogica(generadorRespuestas);
    }

    /**
     * Configura la cadena de manejadores para las solicitudes al servidor.
     */
    private void iniciarManejadorSolicitudesServer() {

        ManejadorSolicitudIniciarPartida manejadorSolicitudIniciarPartida =
                new ManejadorSolicitudIniciarPartida(partidaServerLogica);

        ManejadorSolicitudCasillaSeleccionada manejadorSolicitudCasillaSeleccionada =
                new ManejadorSolicitudCasillaSeleccionada(manejadorSolicitudIniciarPartida, partidaServerLogica);

        ManejadorSolicitudFichaSeleccionada manejadorSolicitudFichaSeleccionada =
                new ManejadorSolicitudFichaSeleccionada(manejadorSolicitudCasillaSeleccionada, partidaServerLogica);

        gestorSolicitudServerProxy = new GestorSolicitudServerProxy(manejadorSolicitudFichaSeleccionada);
    }

    /**
     * Inicializa el generador de respuestas del servidor.
     */
    private void iniciarGeneradorDeRespuestasServer() {
        generadorRespuestas = new GeneradorRespuestas(fachadaServidorProxy);
    }

    /**
     * Establece la conexión con la fachada del servidor.
     */
    private void iniciarConexionServer() {
        fachadaServidorProxy = new FachadaServidorProxy();
        fachadaServidorProxy.agregarListener(gestorSolicitudServerProxy);
    }
}
