package setup;

import domino.fachada.FachadaServidorProxy;
import domino.fachada.IFachadaServidorProxy;
import generadorrespuestas.GeneradorRespuestas;
import generadorrespuestas.IGeneradorRespuestas;
import logica.IPartidaServerLogica;
import logica.PartidaServerLogica;
import manejadorsolicitudserverproxy.GestorSolicitudServerProxy;
import manejadorsolicitudserverproxy.ManejadorSolicitudAbandonarSala;
import manejadorsolicitudserverproxy.ManejadorSolicitudCasillaSeleccionada;
import manejadorsolicitudserverproxy.ManejadorSolicitudCrearSala;
import manejadorsolicitudserverproxy.ManejadorSolicitudFichaSeleccionada;
import manejadorsolicitudserverproxy.ManejadorSolicitudIniciarPartida;
import manejadorsolicitudserverproxy.ManejadorSolicitudUnirseSala;

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
    private IGeneradorRespuestas generadorRespuestas;
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
        partidaServerLogica = new PartidaServerLogica();
    }

    /**
     * Configura la cadena de manejadores para las solicitudes al servidor.
     */
    private void iniciarManejadorSolicitudesServer() {

        ManejadorSolicitudIniciarPartida manejadorSolicitudIniciarPartida =
                new ManejadorSolicitudIniciarPartida(partidaServerLogica);
        
        ManejadorSolicitudUnirseSala manejadorSolicitudUnirseSala =
                new ManejadorSolicitudUnirseSala(manejadorSolicitudIniciarPartida, partidaServerLogica);
        
        ManejadorSolicitudAbandonarSala manejadorSolicitudAbandonarSala =
                new ManejadorSolicitudAbandonarSala(manejadorSolicitudUnirseSala, partidaServerLogica);
        
        ManejadorSolicitudCrearSala manejadorSolicitudCrearSala =
                new ManejadorSolicitudCrearSala(manejadorSolicitudAbandonarSala, partidaServerLogica);

        ManejadorSolicitudCasillaSeleccionada manejadorSolicitudCasillaSeleccionada =
                new ManejadorSolicitudCasillaSeleccionada(manejadorSolicitudCrearSala, partidaServerLogica);

        ManejadorSolicitudFichaSeleccionada manejadorSolicitudFichaSeleccionada =
                new ManejadorSolicitudFichaSeleccionada(manejadorSolicitudCasillaSeleccionada, partidaServerLogica);

        gestorSolicitudServerProxy = new GestorSolicitudServerProxy(manejadorSolicitudFichaSeleccionada);
    }

    /**
     * Inicializa el generador de respuestas del servidor.
     */
    private void iniciarGeneradorDeRespuestasServer() {
        generadorRespuestas = new GeneradorRespuestas();
        partidaServerLogica.setGeneradorRespuestas(generadorRespuestas);
    }

    /**
     * Establece la conexión con la fachada del servidor.
     */
    private void iniciarConexionServer() {
        fachadaServidorProxy = new FachadaServidorProxy();
        generadorRespuestas.setFachadaServidorProxy(fachadaServidorProxy);
        fachadaServidorProxy.agregarListener(gestorSolicitudServerProxy);
    }
}
