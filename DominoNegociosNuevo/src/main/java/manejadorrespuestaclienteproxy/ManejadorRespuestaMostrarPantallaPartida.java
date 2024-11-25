package manejadorrespuestaclienteproxy;

import domino.respuestas.EventoRespuesta;
import domino.respuestas.RespuestaMostrarPantallaPartida;
import mediador.IMediadorNegocio;

/**
 * Clase encargada de manejar la respuesta para mostrar la pantalla de partida.
 * 
 * Esta clase procesa el evento de tipo {@link RespuestaMostrarPantallaPartida} 
 * y solicita al mediador que navegue a la pantalla de la partida.
 * 
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class ManejadorRespuestaMostrarPantallaPartida extends ManejadorRespuestaClienteProxy {

    private IMediadorNegocio mediadorNegocio;

    /**
     * Constructor de la clase.
     *
     * @param mediadorNegocio El mediador de negocio para manejar las transiciones de pantalla.
     */
    public ManejadorRespuestaMostrarPantallaPartida(IMediadorNegocio mediadorNegocio) {
        this.mediadorNegocio = mediadorNegocio;
    }

    /**
     * Constructor con manejador siguiente en la cadena.
     *
     * @param mediadorNegocio El mediador de negocio para manejar las transiciones de pantalla.
     * @param siguienteManejador El siguiente manejador de respuesta en caso de que este no pueda manejar el evento.
     */
    public ManejadorRespuestaMostrarPantallaPartida(IMediadorNegocio mediadorNegocio, ManejadorRespuestaClienteProxy siguienteManejador) {
        super(siguienteManejador);
        this.mediadorNegocio = mediadorNegocio;
    }

    /**
     * Verifica si el evento puede ser manejado por este manejador.
     * 
     * @param evento El evento que se desea procesar.
     * @return {@code true} si el evento es una instancia de {@link RespuestaMostrarPantallaPartida}, 
     *         {@code false} en caso contrario.
     */
    @Override
    protected boolean puedeManejar(EventoRespuesta evento) {
        return evento instanceof RespuestaMostrarPantallaPartida;
    }

    /**
     * Procesa el evento para navegar a la pantalla de partida.
     * 
     * @param evento El evento que contiene la información necesaria para realizar la transición.
     */
    @Override
    protected void procesar(EventoRespuesta evento) {
        mediadorNegocio.irAPartidaDomino();
    }
}
